package med.fema.api.agendamento.service;

import med.fema.api.agendamento.Agendamento;
import med.fema.api.agendamento.repository.AgendamentoRepository;
import med.fema.api.cancelamento.MotivoCancelamento;
import med.fema.api.medico.Medico;
import med.fema.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    private static final LocalTime HORARIO_ABERTURA = LocalTime.of(7, 0);
    private static final LocalTime HORARIO_FECHAMENTO = LocalTime.of(18, 0);

    public List<Agendamento> findAll() {
        List<Agendamento> agendamentos = this.agendamentoRepository.findAllByDataHoraAfterAndAtivoTrueOrderByDataHoraAsc(LocalDateTime.now());
        return agendamentos;
    }

    public List<Agendamento> findAllByPacienteId(Long id) {
        return this.agendamentoRepository.findAllByPacienteIdAndAtivoTrueOrderByDataHoraAsc(id);
    }

    public List<Agendamento> findAllByMedicoId(Long id) {
        return this.agendamentoRepository.findAllByMedicoIdAndAtivoTrueOrderByDataHoraAsc(id);
    }

    public List<Agendamento> findAllByMedicoNomeOrPacienteNome(String nome) {
        return this.agendamentoRepository.findAllByPacienteNomeContainingOrMedicoNomeContainingAndAtivoTrueOrderByDataHoraAsc(nome, nome);
    }

    public Agendamento findById(Long id) {
        Optional<Agendamento> agendamento = this.agendamentoRepository.findById(id);
        if (agendamento.isPresent()) {
            return agendamento.get();
        } else {
            return null;
        }
    }

    public Agendamento criarAgendamento(Agendamento agendamento) throws Exception {
        if(agendamento.equals(null)) {
            return null;
        }
        this.validacoesAgendamento(agendamento);
        if (agendamento.getMedico() == null) {
            List<Medico> medicosDisponiveis = medicoRepository.findMedicosDisponiveis(agendamento.getDataHora());
            if (medicosDisponiveis.isEmpty()) {
                throw new Exception("Não há médicos disponíveis nesse horário.");
            }
            Random random = new Random();
            agendamento.setMedico(medicosDisponiveis.get(random.nextInt(medicosDisponiveis.size())));
        }
        return agendamentoRepository.save(agendamento);
    }

    public void cancelarConsulta(Long id, String motivo) throws Exception {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new Exception("Agendamento não encontrado"));
        if(motivo == null) {
            throw new Exception("Obrigatório informar motivo do cancelamento");
        }
        this.validacoesCancelamento(agendamento, motivo);
        agendamento.setAtivo(false);
    }

    private void validacoesAgendamento(Agendamento agendamento) throws Exception {
        if (agendamento.getDataHora().isBefore(LocalDateTime.now().plusMinutes(30))) {
            throw new Exception("A consulta deve ser agendada com pelo menos 30 minutos de antecedência.");
        }
        if (agendamento.getDataHora().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new Exception("A consulta deve ser agendada entre Segunda e Sábado.");
        }
        LocalTime horario = agendamento.getDataHora().toLocalTime();
        if (horario.isBefore(HORARIO_ABERTURA) || horario.isAfter(HORARIO_FECHAMENTO)) {
            throw new Exception("A consulta deve ser agendada entre 07:00 e 18:00.");
        }
        if (!agendamento.getPaciente().isAtivo()) {
            throw new Exception("O paciente está inativo e não pode agendar consultas.");
        }
        if (!agendamento.getMedico().isAtivo()) {
            throw new Exception("O médico está inativo e não pode realizar consultas.");
        }
        List<Agendamento> consultasNoMesmoDiaPaciente = agendamentoRepository.findByPacienteAndDataHoraAndAtivoTrueOrderByDataHoraAsc(agendamento.getPaciente(), agendamento.getDataHora());
        if (!consultasNoMesmoDiaPaciente.isEmpty()) {
            throw new Exception("Já existe uma consulta agendada para este paciente no mesmo dia.");
        }
        List<Agendamento> consultasComMesmoMedico = agendamentoRepository.findByMedicoAndDataHoraAndAtivoTrueOrderByDataHoraAsc(agendamento.getMedico(), agendamento.getDataHora());
        if (!consultasComMesmoMedico.isEmpty()) {
            throw new Exception("O médico já possui uma consulta agendada nesse horário.");
        }
    }

    private void validacoesCancelamento(Agendamento agendamento, String motivo) throws Exception {
        if (agendamento.getDataHora().isBefore(LocalDateTime.now().plusHours(24))) {
            throw new Exception("A consulta só pode ser cancelada com 24 horas de antecedência.");
        }
        agendamento.setAtivo(false);
        agendamento.setMotivoCancelamento(MotivoCancelamento.fromDescricao(motivo));
        agendamentoRepository.save(agendamento);
    }
}
