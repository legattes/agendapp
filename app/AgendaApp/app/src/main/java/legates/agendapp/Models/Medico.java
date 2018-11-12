package legates.agendapp.Models;

import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Medico {
    private String id;
    private String nome;
    private String cpf;
    private String crm;
    private String telefone;
    private String email;
    private ArrayList<Medico> medicos = new ArrayList<Medico>();
    private Map<String, String> values = new HashMap<>();
    private Service service;
    private Response response;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public ArrayList<Medico> get() {
        service = new Service();
        response = service.get("http://agendapp.legates.com.br/medico/list.php");

        JSONArray json = JSONParser.getKey(response, "medicos");

        for(int i = 0; i < json.length(); i++){
            JSONObject c = new JSONObject();

            try {
                c = json.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Medico medico = new Medico();

            try {
                medico.setId(c.getString("id"));
                medico.setNome(c.getString("nome"));
                medico.setCpf(c.getString("cpf"));
                medico.setCrm(c.getString("crm"));
                medico.setTelefone(c.getString("telefone"));
                medico.setEmail(c.getString("email"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            medicos.add(medico);
        }

        return medicos;
    }

    public boolean remove() {
        values.put("medico_id", this.id);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/medico/remove.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }
        return true;
    }

    public boolean add() {
        values.put("medico_nome", this.nome);
        values.put("medico_cpf", this.cpf);
        values.put("medico_crm", this.crm);
        values.put("medico_telefone", this.telefone);
        values.put("medico_email", this.email);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/medico/add.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }

        return true;
    }

    public boolean edit() {
        values.put("medico_id", this.id);
        values.put("medico_nome", this.nome);
        values.put("medico_telefone", this.telefone);
        values.put("medico_email", this.email);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/medico/edit.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }

        return true;
    }
}
