package legates.agendapp.Models;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Paciente{
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
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
        return getNome();
    }

    public ArrayList<Paciente> get() {
        service = new Service();
        response = service.get("http://agendapp.legates.com.br/paciente/list.php");

        JSONObject reader = new JSONObject();
        try {
            reader = new JSONObject(response.body().string());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray json = new JSONArray();
        try {
            json = reader.getJSONArray("pacientes");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < json.length(); i++){
            JSONObject c = new JSONObject();

            try {
                c = json.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Paciente paciente = new Paciente();

            try {
                paciente.setId(c.getString("id"));
                paciente.setNome(c.getString("nome"));
                paciente.setCpf(c.getString("cpf"));
                paciente.setTelefone(c.getString("telefone"));
                paciente.setEmail(c.getString("email"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            pacientes.add(paciente);
        }

        return pacientes;
    }

    public boolean remove() {
        values.put("paciente_id", this.id);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/paciente/remove.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }
        return true;
    }

    public boolean add() {
        values.put("paciente_nome", this.nome);
        values.put("paciente_cpf", this.cpf);
        values.put("paciente_telefone", this.telefone);
        values.put("paciente_email", this.email);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/paciente/add.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }

        return true;
    }

    public boolean edit() {
        values.put("paciente_id", this.id);
        values.put("paciente_nome", this.nome);
        values.put("paciente_telefone", this.telefone);
        values.put("paciente_email", this.email);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/paciente/edit.php");



        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }

        return true;
    }
}
