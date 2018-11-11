package legates.agendapp.Models;

import android.content.res.Resources;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import legates.agendapp.R;

public class Paciente implements Serializable {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    private OkHttpClient client = new OkHttpClient();

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

    public ArrayList<Paciente> getPacientes(){
        Request request = new Request.Builder().url("http://agendapp.dx.am/paciente/list.php").build();

        Response response = null;

        try{
            response = client.newCall(request).execute();
        } catch (IOException e){
            e.printStackTrace();
        }

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

    public boolean removePaciente(){
        RequestBody requestBody = new FormEncodingBuilder()
                .add("paciente_id", this.id)
                .build();
        Request request = new Request.Builder()
                .url("http://agendapp.dx.am/paciente/remove.php")
                .method("POST",requestBody).header("Content-Length", "0").build();
        try {
            Response response = client.newCall(request).execute();
            if(response.headers().get("Response-Code").equals("420")){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean addPaciente(){
        RequestBody requestBody = new FormEncodingBuilder()
                .add("paciente_nome", this.nome)
                .add("paciente_cpf", this.cpf)
                .add("paciente_telefone", this.telefone)
                .add("paciente_email", this.email)
                .build();
        Request request = new Request.Builder()
                .url("http://agendapp.dx.am/paciente/add.php")
                .method("POST",requestBody).header("Content-Length", "0").build();

        try {
            Response response = client.newCall(request).execute();
            if(response.headers().get("Response-Code").equals("420")){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean editPaciente(){
        RequestBody requestBody = new FormEncodingBuilder()
                .add("paciente_id", this.id)
                .add("paciente_nome", this.nome)
                .add("paciente_telefone", this.telefone)
                .add("paciente_email", this.email)
                .build();
        Request request = new Request.Builder()
                .url("http://agendapp.dx.am/paciente/edit.php")
                .method("POST",requestBody).header("Content-Length", "0").build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if(response.headers().get("Response-Code").equals("420")){
               return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
