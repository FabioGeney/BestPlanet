package com.proyecto.bestplane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Calificacion extends AppCompatActivity {

    private ArrayList<String> tipsSeleccionados = new ArrayList<>();
    private ArrayList<String> tips = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter tipsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Resultados");

        guardaTips();
        seleccoionaTips();
        RatingBar ratingBar = findViewById(R.id.estrallas);
        mRecyclerView = findViewById(R.id.lista);

        String puntaje = getIntent().getStringExtra("rCorrectas");
        ratingBar.setRating(Float.parseFloat(puntaje));
        ratingBar.setClickable(true);

        layoutManager = new LinearLayoutManager(this);

        tipsAdapter  = new TipsAdapter(tipsSeleccionados, R.layout.list_item, new TipsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String mensaje, int position) {

            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(tipsAdapter);

    }

    @Override
    public void onBackPressed() {

    }

    private void guardaTips(){

        tips.add("Antes de comprar un producto, hazte la pregunta si realmente lo necesitas.\n" +
                "\n" +
                "Cualquier consumo innecesario es en esencia antiecológico y antieconómico, ¿no lo crees?.");
        tips.add("Antes de tirar cualquier cosa a la basura, piensa si se puede reutilizar, reciclar o reparar, o si puede ser útil para otra persona.");
        tips.add("Evita las latas de bebidas, vale más el envase que su contenido y apenas se recuperan o reciclan.");
        tips.add("Desconecta los aparatos eléctricos de la red cuando no están funcionando.\n" +
                "\n" +
                "Algunos aparatos (como televisores) siguen gastando hasta un 33% de la energía.");
        tips.add("Prescinde de los electrodomésticos innecesarios como cepillos de dientes, abrelatas, cuchillos eléctricos, y demás chucherías que en vez de “facilitarnos” la vida, nos lleva a consumir y generar más basura electrónica.");
        tips.add("Usa bombillas LED que duran mucho más que las bombillas tradicionales y que inclusiva las fluorescentes.\n" +
                "\n" +
                "Son de bajo consumo de energía para dar la misma cantidad de luz, con lo cual se termina ahorrando dinero y colaborando con el medio ambiente.");
        tips.add("Evita los aerosoles que contienen CFCs y que son causantes de la destrucción de la capa de ozono, u otros gases que también contribuyen al efecto invernadero.");
        tips.add("La gran mayoría de los productos de limpieza que se anuncian no sólo son innecesarios sino también muy nocivos para el medio ambiente.");
        tips.add("Evita usar productos de limpieza agresivos: limpiahornos, lejía, etc., que impiden los procesos biológicos de depuración del agua.");
        tips.add("Nunca tires productos tóxicos, pintura o aceite de cocinar al desagüe.");
        tips.add("Rechaza los alimentos envasados en bandejas de poliestireno. Hay indicios que pueden ser carcinogénicos, además que tardan más de 1.000 años en degradarse.");
        tips.add("Guarda los alimentos en tarros de cristal en lugar de envolverlos o taparlos con plástico o aluminio.");
        tips.add("Evita los productos que recorren grandes distancias antes de llegar al consumidor.\n" +
                "\n" +
                "Ya lo decía, recorren miles de kilómetros consumiendo combustibles que van a agudizar el cambio climático.");
        tips.add("Aprovecha bien el papel: úsalo por las dos caras, utilízalo reciclado y envíalo después a reciclar.");
        tips.add("Evita los productos con PVC. Producen furanos y dioxinas cuando son incinerados.");
        tips.add("Si te ha caducado algún medicamento, no lo tires al desagüe.");
        tips.add("No abuses del aire acondicionado, ya que libera CFCs.");
        tips.add("Utiliza bolsas ecológicas en tus compras.");
        tips.add("Retomemos la costumbre de usar botellas retornables.\n" +
                "\n" +
                "Por cada botella que reutilicempos habremos evitado que las fábricas de plásticos hayan producido contaminantes y hayan aportando al incremento del calentamiento global.");
        tips.add("En muchas ciudades la calidad del agua de grifo es bastante buena, de serlo en tu ciudad toma el agua de ahí y evita el agua embotellada.");
        tips.add("Deja el auto, camina, respira, disfruta del aire libre, has ejercicio y no uses el elevador, sube las escaleras, usarás mejor energía eléctrica y fortalecerás la salud.");
        tips.add("No compres lo que no hace falta.");
        tips.add("Procura consumir tus alimentos y bebidas en el local de expendio de comida, evitarás innecesariamente más embalaje que genera basura como plásticos, cartón y papel.");
        tips.add("Cierra el grifo al cepillarte los dientes o al rasurarte.");
        tips.add("Espera a acumular una cantidad considerable de ropa sucia para usar recién la lavadora.\n" +
                "\n" +
                "Lavar un par de prendas es un desperdicio de agua, energía y detergente.");
        tips.add("Si puedes escoger viajar por avión, tren, bus o tu automóvil, escoge el que menos contamine.");
        tips.add("Si tienes la idea de adquirir un automóvil, elige los modelos compactos y de mayor eficiencia energética en el consumo.");
        tips.add("Cocina la cantidad adecuada para evitar tirar la comida así como compra lo que vayas a consumir para no gastar mas de lo debido como también evitar botar los alimentos.");
        tips.add("Quien tale un árbol deberá plantar dos y así sucesivamente. ¿No sería fenomenal?");
        tips.add("Usa un termo donde puedas utilizar un sinfín de veces al contrario que una botella de agua. ");
        tips.add("¿Te costaría tener cubiertos de metal si comes fuera de casa? Digamos que en la oficina.");
        tips.add("Guarda los envases de vidrio (como los de café por ejemplo) y úsalos para almacenar alimentos.");
        tips.add("Ten en tu auto, oficina, hogar, bolsas de tela y utilízalas.");
        tips.add("Recicla tus electrónicos, dónalos, úsalos hasta que mueran pero no es “cool” comprar cada año el último celular.");
        tips.add("Siempre que puedas compra tus productos con envases de vidrio y no de plástico.");
        tips.add("Reemplaza los cabezales de la ducha por modelos eficientes del gasto de agua.");
        tips.add("Elige plantas nativas para tu jardín que requieran menos agua.");
        tips.add("No dejes corre el agua cuando lavas los platos a mano.");

    }
    private void seleccoionaTips(){

        for (int i = 0; i <10; i++){
            int numero = (int) (Math.random() * 33);
            tipsSeleccionados.add(tips.get(numero));
        }

    }
}
