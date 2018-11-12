package legates.agendapp.Models;

import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Especialidade {
    private String id;
    private String nome;
    private ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
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

    @Override
    public String toString() {
        return this.getNome();
    }

    public ArrayList<Especialidade> get(){
        service = new Service();
        response = service.get("http://agendapp.legates.com.br/especialidade/list.php");

        JSONArray json = JSONParser.getKey(response, "especialidades");

        for(int i = 0; i < json.length(); i++){
            JSONObject c = new JSONObject();

            try {
                c = json.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Especialidade especialidade = new Especialidade();

            try {
                especialidade.setId(c.getString("id"));
                especialidade.setNome(c.getString("nome"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            especialidades.add(especialidade);
        }

        return especialidades;
    }

    public boolean add(){
        values.put("especialidade_nome", this.nome);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/especialidade/add.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }

        return true;
    }

    public boolean remove(){
        values.put("especialidade_id", this.id);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/especialidade/remove.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }
        return true;
    }

    public boolean edit(){
        values.put("especialidade_id", this.id);
        values.put("especialidade_nome", this.nome);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/especialidade/edit.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }

        return true;
    }

    public ArrayList<Especialidade> getByMedico(String id) {
        Map<String, String> values = new HashMap<String, String>();
        values.put("medico_id", id);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/especialidade/listByMedico.php");

        JSONArray json = JSONParser.getKey(response, "especialidades");

        for(int i = 0; i < json.length(); i++){
            JSONObject c = new JSONObject();

            try {
                c = json.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Especialidade especialidade = new Especialidade();

            try {
                especialidade.setId(c.getString("id"));
                especialidade.setNome(c.getString("nome"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            especialidades.add(especialidade);
        }

        return especialidades;
    }
}
