/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author anita elisha, 000786108
 */
public class Lab1 {
    ///<summary>
    ///The main method read data from ELEVATIONS.txt
    ///The file ELEVATION has the data to create values 2 daimational array.
    ///The method calls finfLowerValue, findModeValue, findPeaksValue and calculateDistance methods.
    ///The method dispaly the run time of each method.
    ///</summary>
    ///<param name='values'></param>
    ///<param name='radius'></param>

/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    final String FILENAME = "ELEVATIONS.txt";
    
    int numberOfRows = 0 ;
    int numberOfColumns =0 ;
    int radius = 0;
    int[][] values = null;
    int rows = 0;
    long startTime = System.nanoTime();
    try {
      File file = new File(FILENAME);
      
      try (Scanner inputFile = new Scanner(file)) {
          numberOfRows = inputFile.nextInt();
          numberOfColumns = inputFile.nextInt();
          radius = inputFile.nextInt();
          values = new int[numberOfRows][numberOfColumns];
        while (inputFile.hasNext() && rows < values.length) {
          for (int cols = 0; cols < numberOfColumns; cols++) {
            values[rows][cols] = inputFile.nextInt();
          }
          rows++;
        }
      }
    } catch (Exception ex) {
      System.out.println("Error reading data from " + FILENAME + " Exception = " + ex.getMessage());
    }
    
     // Print the two Array to the screen (using printf to control spcing)
    for (numberOfRows = 0; numberOfRows < values.length; numberOfRows++) {
      for (numberOfColumns = 0; numberOfColumns < values[numberOfRows].length; numberOfColumns++) {
        System.out.printf("%10d ", values[numberOfRows][numberOfColumns]);
      }
      System.out.printf("\n");
    }
        
              
        System.out.println("The lowest peak is. It occurse times in the map");
        System.out.print(Arrays.toString(findLowerValue(values)));
        System.out.printf("\n");
        System.out.println("The minimum distance between two peaks = .");
        System.out.print (Arrays.toString(findPeaksValue(values,radius)));
        System.out.printf("\n");
        System.out.println("The most common height in the in the terrain is occurs times.");
    
        System.out.print(Arrays.toString(findModeValue(values)));
        System.out.printf("\n");
        long stopTime1 = System.nanoTime();
        System.out.printf("Time to execute us");
        System.out.printf("[Time = %d us]\n)" , (stopTime1 - startTime)/1000);
        System.out.println();
    }  
    ///<summary>
    ///The method find the lower value in the array.
    ///the method return the lower value, counter.
    ///The method dispaly the run time of each method.
    ///</summary>
    ///<param name='values'></param>
    ///<returns>int array with lower value in the array and number of accurnce</return>
    
    public static int[] findLowerValue(int[][] values){
        int lowCount = 0 ; // set counter
        int lowerValue = values[1][0]; // set value to comapre
        for (int[] value : values) { // start loop through the array (through rows).
            for (int numberOfColumns = 0; numberOfColumns < value.length; numberOfColumns++) // through columns
            {
                if (value[numberOfColumns] < lowerValue) 
                {
                    lowerValue = value[numberOfColumns]; // find the lower value.
                }
            }
        }
        for (int[] value : values) {
            for (int numberOfColumns = 0; numberOfColumns < value.length; numberOfColumns++) {
                if (lowerValue == value[numberOfColumns]) {
                    lowCount ++;
                }
            }
        }
    
        return new int[] {lowerValue,lowCount};
        
    }
    ///<summary>
    ///The method find the mode value and number of apperance in the array.
    ///the method return the mode, number of accurance.
    ///The method calls return new int array.
    ///the method calculate the run time.
    ///The method dispaly the run time of each method.
    ///</summary>
    ///<param name='values'></param>
    ///<returns>int array with mode and number of accurnce in the array</return>
    
    public static int[] findModeValue(int[][] values){
        int numberOfAccurance = 0 ; //set counter
        int mode = values[1][0];    // set value to satrt search the aaray for the mode.
        for (int[] value : values) {
            for (int numberOfColumns = 0; numberOfColumns < value.length; numberOfColumns++) {
                if (value[numberOfColumns] == mode) // finf the mode
                {
                    mode = value[numberOfColumns]; 
                    numberOfAccurance ++;
                }
            }
        }

    
        return new int[] {mode,numberOfAccurance};
        
    }
    ///<summary>
    ///The method find the peaks value in the array and thier indexes.
    ///the method return the peaks value, indexes.
    ///The method dispaly the run time of method used to execute.
    ///</summary>
    ///<param name='values'></param>
    ///<param name='radius'></param>
    ///<returns>int array with peaksValue,rowPeakValue, colPeakValue </return>
        public static int[] findPeaksValue(int[][] values, int radius){
        
        int peaksValue = 0 ; // peak value
        int rowPeakValue = 0; // row number of peak value position
        int colPeakValue = 0; // column number of peak value postion
       
        
        // start the  Search Criteria through the range of radius.
        for (int innerRows = radius; innerRows <  values.length - radius; innerRows++) 
        {
           
            for (int innerCols = radius; innerCols <  values[innerRows].length - radius ; innerCols++) 
            {
                if (values[innerRows][innerRows] > 90) // Search Criteria #1
                {
                   peaksValue = values[innerRows][innerCols];
                   rowPeakValue = innerRows;
                   colPeakValue = innerCols;
                 
                  
                } 
                // search through out of raduis range
                    for (int outerRows = innerRows-radius+1; outerRows < values.length ; outerRows++) 
                        {
                        for (int outerCols = innerCols-radius+1; outerCols < values[outerRows].length ; outerCols++){
                            // Search Criteria
                            if(values[innerRows-radius][innerCols-radius] > 90){
                                
                               peaksValue = values[innerRows-radius][innerCols-radius] ;
                               rowPeakValue =innerRows-radius;
                               colPeakValue = innerCols-radius;
                            }
                        } 

                    }
                // Search Criteria #2
                
                   if (values[innerRows][innerCols] > values[innerRows-1][innerCols-1] && 
                            values[innerRows][innerCols] > values[innerRows+1][innerCols+1] && values[innerRows][innerRows] > 90 ) // Search Criteria #1
                {
                   peaksValue = values[innerRows][innerCols];
                   rowPeakValue = innerRows;
                   colPeakValue = innerCols;
                  
                  
                } 
                 
      
                }
    
            
               
            }

        
        return new int []{peaksValue,rowPeakValue,colPeakValue} ;
        
        
    }
  
}

