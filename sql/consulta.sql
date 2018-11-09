CREATE TABLE Consulta(
	consulta_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	medico_id bigint NOT NULL,
	paciente_id bigint NOT NULL,
	especialidade_id bigint NOT NULL,
	convenio_id bigint NOT NULL,
	consulta_status varchar(2) NOT NULL DEFAULT 1,
	consulta_data date not null,
	
	CONSTRAINT fk_medico_id_at_consulta FOREIGN KEY (medico_id) REFERENCES Medico (medico_id),
	CONSTRAINT fk_paciente_id_at_consulta FOREIGN KEY (paciente_id) REFERENCES Paciente (paciente_id),
	CONSTRAINT fk_convenio_id_at_consulta FOREIGN KEY (convenio_id) REFERENCES Convenio (convenio_id),
	CONSTRAINT fk_especialidade_id_at_consulta FOREIGN KEY (especialidade_id) REFERENCES Especialidade (especialidade_id)
);