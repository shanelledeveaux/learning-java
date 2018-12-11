import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;



public class InputFileReader extends SalesTaxCalculator{


    public static void InputFileReader( File f ) throws IOException{
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> InputData = new ArrayList<String>();
        List<Double> SalesTax = new ArrayList<Double>();
        List<Double> ItemPrices = new ArrayList<Double>();

        
        while ((line = br.readLine()) != null){
            InputData.add(line);           
        }
        
        for (String item: InputData){
            String[] ItemsArray=item.split(" at ");
            double itemTotal = Double.parseDouble(ItemsArray[1]);
            double importTax = 0.05;
            double regularTax = 0.1;
            

            if(ItemsArray[0].contains("imported")){
                double importedItemTotal = itemTotal * importTax;
                System.out.println()
                // double addtlTax = itemTotal * importTax;
                // SalesTax.add(addtlTax);
                // System.out.println(addtlTax);
            } else if (!ItemsArray[0].contains("book") && !ItemsArray[0].contains("chocolate") && !ItemsArray[0].contains("pill")) {
                double addtlTax = itemTotal * regularTax;
                SalesTax.add(addtlTax);
                // System.out.println("regular tax" + addtlTax);
            }            
            // System.out.println(Arrays.deepToString(ItemsArray));

            ItemPrices.add(itemTotal);
        }

        double taxSum = 0;
        for (double value: SalesTax){
            taxSum+=value;
        }
        System.out.println(taxSum);

        double itemSum = 0;
        for (double value: ItemPrices){
            itemSum+=value;
        }


        System.out.println(Arrays.deepToString(SalesTax.toArray()));
        // System.out.println(Arrays.deepToString(ItemPrices.toArray()));
        // return ItemsArray;
        // System.out.println(taxSum);
        System.out.println(itemSum);
        br.close();
        fr.close();
    }
    
    
    public static void main (String[] args){

        //File reading.
        File f = new File ("../input-data/input3.txt");
        try {
                InputFileReader(f);
            } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }