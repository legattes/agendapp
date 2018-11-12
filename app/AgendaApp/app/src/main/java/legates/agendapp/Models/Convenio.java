package legates.agendapp.Models;

import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Convenio {
    private String id;
    private String nome;
    private String dia_mes_fatura;
    private ArrayList<Convenio> convenios = new ArrayList<Convenio>();
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

    public String getDia_mes_fatura() {
        return dia_mes_fatura;
    }

    public void setDia_mes_fatura(String dia_mes_fatura) {
        this.dia_mes_fatura = dia_mes_fatura;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public ArrayList<Convenio> get(){
        service = new Service();
        response = service.get("http://agendapp.legates.com.br/convenio/list.php");

        JSONArray json = JSONParser.getKey(response, "convenios");

        for(int i = 0; i < json.length(); i++){
            JSONObject c = new JSONObject();

            try {
                c = json.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Convenio convenio = new Convenio();

            try {
                convenio.setId(c.getString("id"));
                convenio.setNome(c.getString("nome"));
                convenio.setDia_mes_fatura(c.getString("dia_mes_fatura"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            convenios.add(convenio);
        }

        return convenios;
    }

    public boolean remove(){
        values.put("convenio_id", this.id);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/convenio/remove.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }
        return true;
    }

    public boolean add(){
        values.put("convenio_nome", this.nome);
        values.put("convenio_dia_mes_fatura", this.dia_mes_fatura);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/convenio/add.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }

        return true;
    }

    public boolean edit(){
        values.put("convenio_id", this.id);
        values.put("convenio_nome", this.nome);
        values.put("convenio_dia_mes_fatura", this.dia_mes_fatura);

        service = new Service();
        response = service.post(values, "http://agendapp.legates.com.br/convenio/edit.php");

        if(response.headers().get("Response-Code").equals("420")){
            return false;
        }

        return true;
    }
}
