import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataExtractor {
    public static void main(String[] args) {
        try {
            File file = new File("data.dat");
            Scanner scanner = new Scanner(file);
            FileWriter myWriter = new FileWriter("data.txt");
            myWriter.write("Email\t\t\t\t\tPassword\tLanguage\tSub Type\t\t\t\tActivation Key\t\tExpiration date\tDevices\n");
            while (scanner.hasNextLine()) {
                String rawData = scanner.nextLine();
                String[] dataInArray = rawData.trim().split("\\|");
                

                {
                    String[] split = dataInArray[0].trim().split(":");
                    myWriter.write(split[0]);
                    for (int i = 0; i < (((40 - split[0].length()) % 8 == 0) ? (40 - split[0].length()) / 8 : ((40 - split[0].length()) / 8) + 1); i++) {
                        myWriter.write("\t");
                    }
                    myWriter.write(split[1]);
                    for (int i = 0; i < (((56 - (40 + split[1].length())) % 8 == 0) ? (56 - (40 + split[1].length())) / 8 : ((56 - (40 + split[1].length())) / 8 + 1)); i++) {
                        myWriter.write("\t");
                    }
                }

                {
                    myWriter.write(dataInArray[1].trim().split("=")[1].trim());
                    myWriter.write("\t\t");
                }

                {
                    String[] subTypeData = dataInArray[2].trim().split("=")[1].trim().replaceAll("\\[", "").replaceAll("]", "").trim().split(",");
                    String[] aKey = dataInArray[3].trim().split("=")[1].trim().replaceAll("\\[", "").replaceAll("]", "").trim().split(",");
                    String[] eDate = dataInArray[4].trim().split("=")[1].trim().replaceAll("\\[", "").replaceAll("]", "").trim().split(",");
                    String[] devices = dataInArray[5].trim().split("=")[1].trim().replaceAll("\\[", "").replaceAll("]", "").trim().split(",");

                    
                    int index = Math.max(Math.max(subTypeData.length, aKey.length), Math.max(eDate.length, devices.length));
                    int check = 0;
                    for (int i = 0; i < index; i++) {
                        myWriter.write((check == 0) ? subTypeData[i].trim() : "\t\t\t\t\t\t\t\t\t" + subTypeData[i].trim());
                        if (i < aKey.length) {
                            int len = ((112 - (72 + subTypeData[i].trim().length())) % 8 == 0) ? (112 - (72 + subTypeData[i].trim().length())) / 8 : (((112 - (72 + subTypeData[i].trim().length())) / 8) + 1);
                            for (int j = 0; j < len; j++)
                                myWriter.write("\t");
                            myWriter.write(aKey[i].trim());
                        }
                        if (i < eDate.length) {
                            int len = ((136 - (112 + aKey[i].trim().length())) % 8 == 0) ? (136 - (112 + aKey[i].trim().length())) / 8 : (((136 - (112 + aKey[i].trim().length())) / 8) + 1);
                            for (int j = 0; j < len; j++)
                                myWriter.write("\t");
                            myWriter.write(eDate[i].trim());
                        }
                        if (i < devices.length) {
                            int len = ((152 - (136 + eDate[i].trim().length())) % 8 == 0) ? (152 - (136 + eDate[i].trim().length())) / 8 : (((152 - (136 + eDate[i].trim().length())) / 8) + 1);
                            for (int j = 0; j < len; j++)
                                myWriter.write("\t");
                            myWriter.write(devices[i].trim());
                        }
                        check = 1;
                        myWriter.write("\n");
                    }

                }
                myWriter.write("\n");
            }
            scanner.close();
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException e) {
            System.out.println("Writing Error");
        }
    }
}
