import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

//this class contains the random race option 1
public class RandomGUI {
    public RandomGUI(){
        JTable table =new JTable();//table
        Object [] columns ={"Driver","Team Name","Staring Place","Place"};//columns
        DefaultTableModel model=new DefaultTableModel();//model

        JFrame frame= new JFrame("Random Race");//title of the frame
        frame.getContentPane().setBackground(new Color(21,41,71));//background color of the frame
        frame.getContentPane().setForeground(Color.WHITE);//foreground color of the frame
        frame.setBounds(100,100,1000,500);//bounds of the frame with the height and width

        frame.getContentPane().setLayout(null);//set layout null
        frame.setLocationRelativeTo(null);//set relative null
        try {//changed the app icon
            frame.setIconImage(ImageIO.read(new File("src/images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Showing date of the race
        JLabel dateLabel=new JLabel();//date label
        dateLabel.setBounds(140,10,200,30);
        dateLabel.setForeground(Color.white);
        frame.getContentPane().add(dateLabel);

        ImageIcon backIcon=new ImageIcon("src/images/back.png");//back icon set
        //back button
        JButton btnBack =new JButton("Back",backIcon);
        btnBack.setBounds(10,10,120,30);
        btnBack.setBackground(new Color(152, 174, 212));
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TableGUI();//move back to the points table
                frame.setVisible(false);//setting invisible the race details

            }
        });

        model.setColumnIdentifiers(columns);
        table.getTableHeader().setBackground(new Color(21,41,71));//column header color
        table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,19));//column header
        table.getTableHeader().setForeground(Color.WHITE);//table column name colors
        table.setModel(model);

        table.setBackground(new Color(139, 144, 196));//set table background color
        table.setForeground(Color.black);//set table foreground color
        table.setSelectionBackground(Color.BLUE);
        table.setGridColor(new Color(21,41,71));
        table.setSelectionForeground(Color.white);
        table.setFont(new Font("Tahoma",Font.PLAIN,17));
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);

        //scroll pane
        JScrollPane pane= new JScrollPane(table);
        pane.setForeground(Color.BLUE);//pane words color
        pane.setBackground(Color.WHITE);//pane background color
        pane.setBounds(0,50,1000,450);
        frame.getContentPane().add(pane);

        //shuffling the participants for starting position
        Formula1ChampionshipManager data=new Formula1ChampionshipManager();//to call some methods in this class
        Formula1Driver[] participants =new Formula1Driver[Formula1ChampionshipManager.formula1DriversList.size()];//array for starting position with drivers to the race object
        Collections.shuffle(Formula1ChampionshipManager.formula1DriversList);//shuffling drivers to change the starting places of the drivers

        int count=0;
        for (Formula1Driver formula1Driver: Formula1ChampionshipManager.formula1DriversList) {
           participants[count]=formula1Driver;// keeping in the stating position with driver
           count++;
        }

        ArrayList<Integer> places= endingPositions(Formula1ChampionshipManager.formula1DriversList.size());// shuffled integers for winning positions

        Formula1Driver[] driverSortedWithPlace=new Formula1Driver[Formula1ChampionshipManager.formula1DriversList.size()];

        Object[] row =new Object[4];// to give values in a row

        for (int i = 0; i< Formula1ChampionshipManager.formula1DriversList.size(); i++) {
            row[0]=participants[i].getName();//name of the driver in the given position
            row[1]=participants[i].getTeam();//team of the driver in the given starting position
            row[2]=i+1;//the starting place
            row[3]=places.get(i);//get random position

            Formula1ChampionshipManager.formula1DriversList.get(i).setNumOfPointsByPlace(places.get(i));//pass the stats for driver
            driverSortedWithPlace[places.get(i) -1]= Formula1ChampionshipManager.formula1DriversList.get(i);//drivers sorted within the winning places to pass to the race object

            model.addRow(row);// add the row to the table

        }
        //setting a date after sep-2021 to get the random date
        Random random=new Random();
        String dateSample = "/09/2021";//setting the minimum date for random date
        String dateInString=random.nextInt(100)+1+ dateSample;
        try {
            //adding the race to the list
            Formula1ChampionshipManager.racesList.add(new Race(dateInString,driverSortedWithPlace));//add date to the race details

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {//setting the date label
            Date raceDay = new SimpleDateFormat("dd/MM/yyyy").parse(dateInString);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");//formatted date
            dateLabel.setText("Date: "+simpleDateFormat.format(raceDay));//setting
        } catch (ParseException e) {
            e.printStackTrace();
        }

        data.saveData();// to save data to the file
        data.saveRaceData();//saving races to the file
        frame.setVisible(true);//showing the frame

    }

    ArrayList<Integer> endingPositions(int length){
        //to get shuffled unique numbers to a given size of the participants
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=1; i<length+1; i++) {//adding numbers 1 to number of participants
            list.add(i);
        }
        Collections.shuffle(list);//shuffle the numbers
        return list;//returning the array list contains winning positions
    }
}
