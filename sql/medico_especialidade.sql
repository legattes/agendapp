CREATE TABLE Medico_Especialidade(
medico_id bigint not null,
especialidade_id bigint not null,
    
    CONSTRAINT fk_medico_id_at_medico_especialidade FOREIGN KEY (medico_id) REFERENCES Medico (medico_id),
	CONSTRAINT fk_especialidade_id_at_medico_especialidade FOREIGN KEY (especialidade_id) REFERENCES Especialidade (especialidade_id)
);