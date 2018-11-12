package legates.agendapp.Models;

import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Consulta {
    private String id;
    private String medico;
    private String paciente;
    private String especialidade;
    private String convenio;
    private String status;
    private String data;
    private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
    private Map<String, String> values = new HashMap<>();
    private Service service;
    private Response response;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDia(){
        String[] split = this.data.split(" ");
        String[] format = split[0].split("-");
        String dia = format[2] + "/" + format[1]  + "/" + format[0];
        return dia;
    }

    public String getHora(){
        String[] split = this.data.split(" ");
        String[] format = split[1].split(":");
        String hora = format[0] + ":" + format[1];
        return hora;
    }

    @Override
    public String toString() {
        return this.getData();
    }

    public ArrayList<Consulta> get(){
        service = new Service();
        response = service.get("http://agendapp.legates.com.br/consulta/list.php");

        JSONArray json = JSONParser.getKey(response, "consultas");

        for(int i = 0; i < json.length(); i++){
            JSONObject c = new JSONObject();

            try {
                c = json.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Consulta consulta = new Consulta();

            try {
                consulta.setId(c.getString("consulta_id"));
                consulta.setMedico(c.getString("medico_nome"));
                consulta.setPaciente(c.getString("paciente_nome"));
                consulta.setEspecialidade(c.getString("especialidade_nome"));
                consulta.setConvenio(c.getString("convenio_nome"));
                consulta.setStatus(c.getString("consulta_status"));
                consulta.setData(c.getString("consulta_data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            consultas.add(consulta);
        }

        return consultas;
    }
}
