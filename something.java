import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;



public class InputFileReader {


    public static void InputFileReader( File f ) throws IOException{
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> InputData = new ArrayList<String>();
        List<Double> SalesTax = new ArrayList<Double>();
        List<Double> ItemPrices = new ArrayList<Double>();
        HashMap<String, Double> reciept = new HashMap <String, Double>();

        
        while ((line = br.readLine()) != null){
            InputData.add(line);           
        }
        
        for (String item: InputData){
            String[] ItemsArray=item.split(" at ");
            double itemWOTax = Double.parseDouble(ItemsArray[1]);
            double importTax = 0.05;
            double regularTax = 0.1;
            
            //IF ITEM IS EXCLUDED
            if(ItemsArray[0].contains("book") || ItemsArray[0].contains("chocolate") || ItemsArray[0].contains("pill")){
                //AND THE ITEM IS IMPORTED
                if(ItemsArray[0].contains("imported")){
                    double addtlTax = itemWOTax * importTax;
                    SalesTax.add(addtlTax);
                    double itemTotal = itemWOTax + addtlTax;
                    ItemPrices.add(itemTotal);             
                    reciept.put(ItemsArray[0], itemTotal);    
                //IF THE ITEM IS ONLY EXCLUDED
                } else {
                    ItemPrices.add(itemWOTax);
                    reciept.put(ItemsArray[0], itemWOTax);  
                }
            // IF THE ITEM IS ONLY IMPORTED BUT NOT EXCLUDED
            } else if (ItemsArray[0].contains("imported")) {
                double addtlTax = itemWOTax * (importTax + regularTax);
                    SalesTax.add(addtlTax);
                    double itemTotal = itemWOTax + addtlTax;
                    ItemPrices.add(itemTotal);
                    reciept.put(ItemsArray[0], itemTotal);   
                
            //REGULAR ITEM
            } else {
                double addtlTax = itemWOTax * regularTax;
                SalesTax.add(addtlTax);
                double itemTotal = addtlTax + itemWOTax;
                ItemPrices.add(itemTotal);
                reciept.put(ItemsArray[0], itemTotal); 
            }           
            // System.out.println(Arrays.deepToString(ItemsArray));

            // ItemPrices.add(itemTotal);
        }
        System.out.println(Arrays.asList(reciept));

        double taxSum = 0;
        for (double value: SalesTax){
            taxSum+=value;
        }

        double itemSum = 0;
        for (double value: ItemPrices){
            itemSum+=value;
        }

        System.out.println(taxSum);
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