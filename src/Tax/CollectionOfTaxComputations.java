package Tax;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionOfTaxComputations {

    private static HashMap<String, TaxComputation> taxCompClasses = new HashMap<String, TaxComputation>();
    private static String pkg = "Tax.States.";
    private static ArrayList<String> states = new ArrayList<String>();

    // Map of all State Tax Computations
    public static HashMap<String, TaxComputation> getMapOfTaxComputations() {
        states.add("CA"); // California
        states.add("MA"); // Massachusetts
        states.add("MD"); // Maryland
        states.add("DE"); // Delaware

        for(String stateCode: states) {
            addStateToTaxCompHashMap(stateCode);
        }
        return taxCompClasses;
    }

    private static void addStateToTaxCompHashMap(String stateCode) {
        String className = pkg + stateCode + "TaxComputation";
        try {
            Class taxClass;
            TaxComputation TaxComputationToAdd;

            taxClass = Class.forName(className);
            TaxComputationToAdd = (TaxComputation) taxClass.newInstance();

            taxCompClasses.put( stateCode, TaxComputationToAdd );

        }catch(ClassNotFoundException e) {
            System.out.println("Tax comp error");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
