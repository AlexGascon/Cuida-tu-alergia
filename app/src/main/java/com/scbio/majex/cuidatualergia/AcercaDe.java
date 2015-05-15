package com.scbio.majex.cuidatualergia;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class AcercaDe extends Activity {
    /** Called when the activity is first called*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);

        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        int tipoPolen = prefs.getInt("polen", 0);
        String stringPolen;

         switch(tipoPolen) {
             case 0:
                 stringPolen = "Florece durante el otoño, fundamentalmente en los meses de octubre a diciembre. En la Península Ibérica está muy extendido su uso en las regiones del " +
                         "litoral mediterráneo y se cultiva como árbol de paseos, parques y jardines. Arbol de aspecto muy parecido a un pino.";
                 break;
             case 1:
                 stringPolen = "Las Cupresáceas son árboles de hoja perenne, generalmente muy pequeñas y con forma de escama. El periodo de presencia del polen aerotransportado de las " +
                         "cupresáceas es en la mayoría de las ocasiones muy prolongado, de varios meses, debido a que muchas especies tienen épocas de floración sucesivas. Meses de especial cuidado: Enero y Febrero";
                 break;
             case 2:
                 stringPolen = "El periodo de presencia atmosférica del polen de fresno en toda España se extiende generalmente de finales de noviembre hasta mayo del año siguiente," +
                         " ya que incluye tres periodos de floración sucesivos. En España, se encuentra en todas las zonas de clima mediterráneo llegando en el norte hasta el sur de Galicia y los valles inferiores de Pirineos. Fraxinus ornus, en la península, sólo se encuentra en las montañas de la Comunidad Valenciana. Meses de especial cuidado: Enero y Febrero.";
                 break;
             case 3:
                 stringPolen = "La alergenicidad del polen de ligustrum es mucho menor que la del olivo y la sensibilización a este tipo suele estar asociada a sensibilización al polen de olivo.\n" +
                         "La estación de floración se inicia en la zona Mediterránea en los meses de mayo-junio.\n";
                 break;
             case 4:
                 stringPolen = "Todas son especies cultivadas en calles, parques y jardines. Las hojas de la morera se utilizan para alimentar gusanos de seda y los frutos son comestibles. Al polen de las moreras" +
                         " se le atribuye capacidad alergénica baja. Meses de especial cuidado: Abril y Mayo.";
                 break;
             case 5:
                 stringPolen = "El olivo es un árbol de hoja perenne que crece hasta una altura de 10 metros y que tiene una copa grande y redondeada y un tronco grueso y nudoso." +
                         "El polen del olivo provoca a menudo fiebre del heno, asma y conjuntivitis en personas sensibles. Crecen en plantaciones y bosques, y como arbustos en lugares secos y rocosos.\n" +
                         "Meses de especial cuidado: Mayo, Junio.\n";
                 break;
             case 6:
                 stringPolen = "POSAR ACÍ EL VALOR DE LA INFO";
                 break;
             case 7:
                 stringPolen = "Especie introducida, de cultivo común como árbol de sombra, de alineación en bordes de carretera. La floración suele comenzar durante la segunda quincena de marzo o la primera de abril y la emisión de polen es tan grande, que las concentraciones atmosféricas que alcanza son mayores que las de cualquier otro tipo polínico. " +
                         "Meses de especial cuidado: Marzo, Abril.";
                 break;
             case 8:
                 stringPolen = "Populus nigra es cultivada como ornamental, árbol de sombra en parques, avenidas, carreteras, etc. Como maderable en vegas y lugares con niveles freáticos accesibles y no salinos. Es árbol del zócalo basal y de meseta. " +
                         "Meses de especial cuidado: Febrero y Marzo.";
                 break;
             case 9:
                 stringPolen = "Meses de especial cuidado: Abril, Mayo y Junio.";
                 break;
             case 10:
                 stringPolen = "Los olmos son populares en el paisajismo, debido al valor decorativo de sus hojas y la sombra que proporcionan. Asimismo, la madera dura de esta especie es valorada en términos comerciales. " +
                         "El olmo chino es una de las 45 especies de olmos, y el polen que produce genera alergias fuertes. Meses de especial cuidado: Marzo, Abril.";
                 break;


         }
        TextView info = (TextView) findViewById(R.id.TextView01)
        info.setText(stringPolen)



    }

}import android.app.Activity;
import android.os.Bundle;

