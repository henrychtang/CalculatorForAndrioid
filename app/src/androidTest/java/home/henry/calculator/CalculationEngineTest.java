package home.henry.calculator;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Henry on 4/2/2018.
 */

public class CalculationEngineTest {

    @Test
    public void ableAnalyseQuery(){
        Log.i("test", "abc123");
        String queryString="11+2x3";
        CalculationEngine calculationEngine=new CalculationEngine();
        calculationEngine.analyseQueryString(queryString);

    }

}