package Heap;

import java.io.*;
import java.util.*;

public class Heap 
{
   public static class MinHeap 
   {
       private final int array[];
       private final int arraySize;
       private int heapSize;
  
       private final int INF = 9999999;

       public MinHeap(int arraySize) 
       {
           this.arraySize = arraySize;
           this.array = new int[arraySize + 1];
           for (int i = 0; i < arraySize + 1; i++) 
                this.array[i] = this.INF;
       }
   
        private void sort() 
        {
            int x = (int) Math.floor(this.heapSize / 2.0);
            while (x != 0) 
            {
                if (this.array[x * 2] != this.INF)
                    if (this.array[x] > this.array[x * 2]) 
                        this.swap(x, x * 2);
                if (this.array[(x * 2) + 1] != this.INF)
                    if (this.array[x] > this.array[(x * 2) + 1]) 
                        this.swap(x, (x * 2) + 1);
                x--;
            }
        }
   
        private void swap(int index1, int index2) 
        {
            int temp = this.array[index1];
            this.array[index1] = this.array[index2];
            this.array[index2] = temp;
        }
   
        public void printArray()
        {
            for (int i=0;i< this.arraySize;i++)
                 System.out.print(this.array[i]+" ");
            System.out.println("\n\n");
        }
   
        public void insert(int element) 
        {
            if (heapSize >= arraySize)
                System.out.println("heap is full");
            else 
            {
               this.heapSize += 1;
               array[heapSize] = element;
               this.sort();
            }
        }

        public void decreaseKey(int index,int newElement)
        {
            if (this.array[index+1] != this.INF )
            {
                this.array[index+1] -= newElement;
                this.sort();
            }
            else 
                System.out.println("there is no element at index "+ index);
        }
        public int extractMin()
        {
            if (heapSize < 1)
            {
                System.out.println("heap is empty");
                return 0;
            }
            else 
            {
                int min = this.array[1];
                this.array[1] = this.array[this.heapSize];
                this.array[this.heapSize] = this.INF;
                this.heapSize -= 1;
                sort();
                return min;
            }
        }   
   }
   
   
   public static void main(String args[]) throws FileNotFoundException 
   {
       int heapSize = 100, numInstructions = 0;
       MinHeap minHeap = new MinHeap(heapSize);
       File in = new File("inputFile.txt");
       Scanner scan = new Scanner(in);
       if (scan.hasNextLine())
           numInstructions = Integer.parseInt(scan.nextLine());
       while (numInstructions >= 1 && scan.hasNextLine())
       {   
              String instruction = scan.nextLine();
              String[] operands = instruction.split(" ");
              if (operands[0].equals("IN"))
                  minHeap.insert(Integer.parseInt(operands[1]));
              else if (operands[0].equals("DK"))
                  minHeap.decreaseKey(Integer.parseInt(operands[1]), Integer.parseInt(operands[2]));
              else if (operands[0].equals("EM"))
              {
                  int LastEM = minHeap.extractMin();
                  if (numInstructions == 1)
                      System.out.println(LastEM);
              }
             numInstructions--;  
       }
       scan.close();
   }
}

/*
        Reference for combining heapifyUp and heapifyDown funtions, came from GeeksForGeeks.
                Link: https://www.geeksforgeeks.org/k-ary-heap/  
        Reference for converting binary heap methods to K-ary heaps inspired by Jorie Noll.
                Link: https://github.com/jorienoll/kary-heap/blob/main/karyHeap.java
                      
*/