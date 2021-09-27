package com.csvreader;

import java.io.*;
import java.awt.Color;

import au.com.bytecode.opencsv.CSVReader;  
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Font;

public class App {
    public static void main(String[] args) throws Exception {
        CSVReader reader = null;

        //create document with aspose-words
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        Font font = builder.getFont();
        font.setSize(11);
        font.setColor(Color.BLACK);
        font.setName("Arial");

        //create document constants
        String[] questions = {
            "How would you describe your post COVID-19 recovery plan? How does it ensure equity, safety, and long-term economic security for immigrant communities and communities of color? How are you incorporating the voices and needs of these communities in your plans?",
            "Over the course of the pandemic, there has been a rise of xenophobia and anti-immigrant sentiment, in large part due to disinformation about the origins of COVID-19. Meanwhile, many immigrant communities and communities of color lack access to culturally competent and language accessible resources about COVID-19, which contributes to vaccine hesitancy. How will you use your position to ensure the health, safety, and wellbeing of our immigrant and refugee communities?",
            "Given the local police killings in recent years of Charleena Lyles, Manuel Ellis, Tommy Le, and many others, how do you plan to address police violence, the use of lethal force, and the need for accountability?",
            "Public schools often reflect the racial and class inequities of the communities they serve. Schools in low income tax brackets have less funding and disproportionately prioritize security presence over social services, which contributes to overcriminalization of black and brown youth and significant opportunity gaps. How would you use your influence over budget allocation to address these systemic issues in the education system?",
            "While technology can bring innovation that helps improve our daily lives, we must also consider the potential for misuse of personal information and unregulated mass surveillance. Technology abuse and surveillance have disproportionately impacted Muslim, Black, Brown, Native, and other marginalized communities across the United States. How do you plan to control the proliferation of dangerous biometric and AI technologies and limit the possibility of abuse?",
            "How has your campaign worked so far to engage Muslim communities?"
        };
        String line = "-------------------------------------------------------------------------------------------------------------------------------";

        try {
            //parsing a CSV file into CSVReader class constructor  
            reader = new CSVReader(new FileReader("/Users/shoaiblaghari/Desktop/code/csvreader/csvreader.csv"));
            String[] nextLine;
            //reads one line at a time  
            while ((nextLine = reader.readNext()) != null) {
                //write relevant material to document
                font.setBold(true);
                builder.write("Candidate: ");
                font.setBold(false);
                builder.writeln(nextLine[1]);
                font.setBold(true);
                builder.write("Email: ");
                font.setBold(false);
                builder.writeln(nextLine[2]);
                font.setBold(true);
                builder.write("Website: ");
                font.setBold(false);
                builder.writeln(nextLine[3]);
                builder.writeln(); //empty line before responses

                //handles all 6 questions per candidate
                for (int i = 1; i < 7; i++) {
                    font.setUnderline(1);
                    font.setBold(true);
                    builder.writeln("Question #" + i + ":");
                    font.setUnderline(0);
                    font.setBold(false);
                    builder.writeln(questions[i-1]);
                    font.setUnderline(1);
                    font.setBold(true);
                    builder.writeln("Answer:");
                    font.setUnderline(0);
                    font.setBold(false);
                    builder.writeln(nextLine[i+4]);
                    builder.writeln();
                }

                builder.writeln(line);
                builder.writeln();
            }
            doc.save("csvreader.docx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}