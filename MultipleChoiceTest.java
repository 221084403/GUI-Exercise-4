/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author MNCEDISI
 */
public class MultipleChoiceTest extends JFrame
{
    private JPanel headingPNL;
    private JPanel namePNL;
    private JPanel headingAndNameContainerPNL;
    private JPanel headingAndNamePNL;
    private JPanel questionGridPNL;
    private JPanel questionGridContainerPNL;
    private JPanel buttonsPNL;
    private JPanel questionsGridAndButtonPNL;
    private JPanel areaTxtPNL;
    
    private JLabel headingLBL;
    private JLabel nameLBL;
    private JLabel questionLBL;
    private JLabel questionToAnswerLBL;
   
    private JTextField nameTxtFLD;
    private JTextField questionAnswerTxtFLD;
    
    private JButton  startBTN;
    private JButton  nextBTN;
    private JButton  doneBTN;
    private JButton  exitBTN;
    
    private JTextArea area;
    
    private JScrollPane scrollAreaSLP;

    private ArrayList<String> theQuestions;
    private ArrayList<String> storeInfor;
    
    private Random random ;
    private int count;
    private int position;
    private int index;
    
    private int correctAnswers;
    private int wrongAnswers;
    
    private String userAnswer;
    private String lecAnswer;
    
    public MultipleChoiceTest() 
    {
        setTitle("Multiple Choice Test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(500, 520);
        setResizable(false);
        
        //Creating a lists
        //theAnwers = new ArrayList<>();
        theQuestions = new ArrayList<>();
        storeInfor = new ArrayList<>();
        random = new Random();
        
        //Initialling values
        index = 0;
        position = 0;
        correctAnswers = 0;
        wrongAnswers = 0 ;
      
        //Creating a panel
        headingPNL = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namePNL  = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePNL.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 3), "Student Name"));
        namePNL.setPreferredSize(new Dimension(480, 60));
        headingAndNamePNL = new JPanel(new GridLayout(2, 1 , 1, 1));
        headingAndNameContainerPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        questionGridPNL = new JPanel(new GridLayout(3, 1,1,1));
        questionGridContainerPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionGridContainerPNL.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 3), "Answer Below"));
        questionGridContainerPNL.setPreferredSize(new Dimension(480 , 100));
        questionsGridAndButtonPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPNL = new JPanel(new FlowLayout(FlowLayout.LEFT ,15, 5));
        areaTxtPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        //Creating a label
        headingLBL  = new JLabel("Web Test");
        headingLBL.setFont(new Font(null, Font.BOLD, 30));
        headingLBL.setForeground(Color.GREEN);
        
        nameLBL = new  JLabel("Student Name ");
        
        questionLBL  = new JLabel("Press[start] to start");
        questionToAnswerLBL = new JLabel("No question");
        
        //Creating a textfield
        nameTxtFLD = new JTextField(10);
        questionAnswerTxtFLD = new JTextField(10);
        
        //Creating button
        startBTN = new JButton("START");
        startBTN.addActionListener(new StartBTN());
        
        nextBTN = new JButton("NEXT");
        nextBTN.addActionListener(new NextBTN());
        
        doneBTN = new JButton("DONE"); 
        doneBTN.addActionListener(new DoneBTN());
        
        exitBTN = new JButton("EXIT"); 
        exitBTN.addActionListener(new ExitBTN());
        
        //Creating a text area
        area = new JTextArea(10, 30);
        area.setBorder(new LineBorder(Color.BLUE, 3));
        area.setEditable(false);
        
        //Creating a scrollPane
        scrollAreaSLP = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollAreaSLP.setPreferredSize(new Dimension(480, 200));
        
        namePNL.add(nameLBL);
        namePNL.add(nameTxtFLD);
        
        headingPNL.add(headingLBL);
     
        headingAndNamePNL.add(headingPNL);
        headingAndNamePNL.add(namePNL);
        
        //first Collectives
        headingAndNameContainerPNL.add(headingAndNamePNL);
        
        questionGridPNL.add(questionLBL);
        questionGridPNL.add(questionToAnswerLBL);
        questionGridPNL.add(questionAnswerTxtFLD);
        
        //Second Collectives
        questionGridContainerPNL.add(questionGridPNL);
      
        buttonsPNL.add(startBTN);
        buttonsPNL.add(nextBTN);
        buttonsPNL.add(doneBTN);
        buttonsPNL.add(exitBTN);
        
        questionsGridAndButtonPNL.add(questionGridContainerPNL , BorderLayout.NORTH);
        questionsGridAndButtonPNL.add(buttonsPNL , BorderLayout.SOUTH);
    
        //Third Collectives
        areaTxtPNL.add(scrollAreaSLP);
        
        add(headingAndNameContainerPNL , BorderLayout.NORTH);
        add(questionsGridAndButtonPNL , BorderLayout.CENTER);
        add(areaTxtPNL , BorderLayout.SOUTH);
        
        setVisible(true);
    }

    private  class ExitBTN implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.exit(0);
        }
    }

    private class DoneBTN implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String outcome = "";
            String name = nameTxtFLD.getText();
            
            int totalQuestions  = correctAnswers + wrongAnswers;
            
            double percentage = ((double)correctAnswers /totalQuestions) * 100.0 ;
            
            outcome+="The name of the student  :"+name+"\n"+
                     "The number of questions asked :"+totalQuestions+"\n"+
                     "The number of correct answers  :"+correctAnswers+"\n"+
                     "The percentage mark obtained     :"+(int)percentage+"%\n";
            
            for (String display : storeInfor) 
                outcome +=display;
            
            area.setText(outcome);
        }
    }

    private class NextBTN implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            position++;
            questionAndAnswer();
            questionAnswerTxtFLD.setText(null);
        }

        private void questionAndAnswer() 
        {   
            int index = random.nextInt(7);

            if(position<=5)
            {
                String data[] = theQuestions.get(index).split(":");
               
                String lecAnswer = data[1];
                String question = data[0];

                questionLBL.setText("Question "+position);

                questionToAnswerLBL.setText(data[0]);
                userAnswer = questionAnswerTxtFLD.getText();

                compareAnswers(lecAnswer , userAnswer , question);
            }
            else
            {
                questionLBL.setText("Press[done] to view outcome");
                questionToAnswerLBL.setText("No Questions");
                questionAnswerTxtFLD.setText("Dont answer");
            }
                
            
        }

        private void compareAnswers(String lecAnswer, String userAnswer , String question) 
        {
            String outcome = "";
            String storeResultQuestion = "";
            
            if(lecAnswer.equalsIgnoreCase(userAnswer))
            {
                correctAnswers++;
                outcome = "Correct";    
            }
            else
            {
                wrongAnswers ++;
                outcome ="Wrong";
            }
            
            storeResultQuestion = "\nQuestion "+ position+ "\n"+
                                  question+"\n"+
                                  "Answer :"+lecAnswer+"\n"+
                                  "Student Answer :"+userAnswer+"\n"+
                                  "Result :"+outcome+"\n";
            
            storeInfor.add(storeResultQuestion);
            
        }
    }

    private class StartBTN implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            theQuestions.add("1 + 1 = ?  A.1  B.11  C.2  D.0 :C");
            theQuestions.add("1 * 1 = ?  A.1  B.11  C.2  D.0 :A");
            theQuestions.add("1 / 1 = ?  A.1  B.11  C.2  D.0 :A");
            theQuestions.add("1 - 1 = ?  A.1  B.11  C.2  D.0 :D");
            theQuestions.add("1 % 1 = ?  A.1  B.11  C.2  D.0 :D");
            theQuestions.add("(1 + 1) * 2 = ?  A.1  B.2  C.4  D.6 :C");
            theQuestions.add("(1 + 1) / 2 = ?  A.1  B.2  C.4  D.6 :A");
            
            questionLBL.setText("No Question ");
            questionToAnswerLBL.setText("Press[Next] to start");
            
            count = 0;
  
        }
    }
}
