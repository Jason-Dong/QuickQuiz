import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;


public class QQMenu
{
    private JFrame frame;

    private ProblemDatabase problem;

    private Stack<Problem> back;

    private Stack<Problem> forward;

    private int index = 0;

    JLabel problemImage;

    private Timer timer;

    private int timerCount;

    JLabel timerDisplay;

    JLabel scoreDisplay;

    private int totalScore;


    public QQMenu( ProblemDatabase problem )
    {
        // this.problem = problem;
        //
        //
        // index = 0;
        //
        // for (int i = 0; i < 25; i++)
        // {
        // forward.push( problem.giveRandProblem() );
        // }

        JButton back = new JButton( "Back" );
        back.setBounds( 10, 10, 50, 50 );
        back.addActionListener( new BackButtonListener() );

        JButton choice1 = new JButton( "A" );
        choice1.setBounds( 150, 500, 100, 50 );
        choice1.addActionListener( new AButtonListener() );

        JButton choice2 = new JButton( "B" );
        choice2.setBounds( 250, 500, 100, 50 );
        choice2.addActionListener( new BButtonListener() );

        JButton choice3 = new JButton( "C" );
        choice3.setBounds( 350, 500, 100, 50 );
        choice3.addActionListener( new CButtonListener() );

        JButton choice4 = new JButton( "D" );
        choice4.setBounds( 450, 500, 100, 50 );
        choice4.addActionListener( new DButtonListener() );

        JButton choice5 = new JButton( "E" );
        choice5.setBounds( 550, 500, 100, 50 );
        choice5.addActionListener( new EButtonListener() );

        JButton prevProb = new JButton( "Previous Problem" );
        prevProb.setBounds( 250, 450, 150, 50 );
        prevProb.addActionListener( new PrevButtonListener() );

        JButton nextProb = new JButton( "Next Problem" );
        nextProb.setBounds( 400, 450, 150, 50 );
        nextProb.addActionListener( new NextButtonListener() );

        timerDisplay = new JLabel( "Time: 0:00" );
        timerDisplay.setFont( new Font( "font", Font.PLAIN, 18 ) );
        timerDisplay.setBounds( 650, 10, 150, 50 );
        timerDisplay.setHorizontalAlignment( JLabel.CENTER );

        scoreDisplay = new JLabel( "Score: 0" );
        scoreDisplay.setBounds( 300, 400, 200, 50 );
        scoreDisplay.setHorizontalAlignment( JLabel.CENTER );

        frame = new JFrame( "Quick Quiz Menu" );
        frame.setDefaultCloseOperation( 0 );
        Container c = frame.getContentPane();
        frame.setLayout( null );
        frame.setBounds( 0, 0, 800, 600 );

        c.add( back );
        c.add( choice1 );
        c.add( choice2 );
        c.add( choice3 );
        c.add( choice4 );
        c.add( choice5 );
        c.add( prevProb );
        c.add( nextProb );
        c.add( scoreDisplay );
        c.add( timerDisplay );

        ImageIcon icon = createImageIcon( "images/2016_1.png", "image" );
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance( 680, 300, Image.SCALE_DEFAULT );
        icon.setImage( newImage );

        int number = index + 1;

        problemImage = new JLabel( "Problem " + number, icon, JLabel.CENTER );
        problemImage.setFont( new Font( "font", Font.PLAIN, 18 ) );
        problemImage.setBounds( 60, 60, 680, 340 );
        problemImage.setVerticalTextPosition( JLabel.BOTTOM );
        problemImage.setHorizontalTextPosition( JLabel.CENTER );

        c.add( problemImage );

        frame.setResizable( false );
        frame.setVisible( true );

        TimerListener time = new TimerListener();
        timer = new Timer( 1000, time );
        timer.start();

    }


    private void updateSolutionsPage(Problem prob, boolean forward)
    {
        timer.stop();

        scoreDisplay.setText( "Score: " + totalScore );
        scoreDisplay.setBounds( 300, 400, 200, 50 );
        scoreDisplay.setHorizontalAlignment( JLabel.CENTER );

        JButton back = new JButton( "Back" );
        back.setBounds( 10, 10, 50, 50 );
        back.addActionListener( new BackButtonListener() );

        JButton prevProb = new JButton( "Previous Problem" );
        prevProb.setBounds( 250, 500, 150, 50 );
        prevProb.addActionListener( new PrevButtonListener() );

        JButton nextProb = new JButton( "Next Problem" );
        nextProb.setBounds( 400, 500, 150, 50 );
        nextProb.addActionListener( new NextButtonListener() );

        JButton viewSol = new JButton( "See Solution" );
        viewSol.setBounds( 300, 450, 200, 50 );
        viewSol.addActionListener( new ViewButtonListener() );

        frame = new JFrame( "Quick Quiz Menu" );
        frame.setDefaultCloseOperation( 0 );
        Container c = frame.getContentPane();
        frame.setLayout( null );
        frame.setBounds( 0, 0, 800, 600 );

        c.add( back );
        c.add( viewSol );
        c.add( problemImage );
        c.add( scoreDisplay );

        frame.setVisible( true );
    }


    private void updateScore( boolean correct, int time )
    {
        if ( correct )
        {
            if ( time <= 180 )
            {
                totalScore += 100;
            }
            else
            {
                totalScore += 50;
            }
        }
    }


    private void updateProblem( Problem prob )
    {
        Container c = frame.getContentPane();

        // ImageIcon icon = createImageIcon( prob.getProblemImage(), "image" );
        // Image image = icon.getImage();
        // Image newImage = image.getScaledInstance( 680, 300,
        // Image.SCALE_DEFAULT );
        // icon.setImage( newImage );

        ImageIcon icon = createImageIcon( "images/2016_1.png", "image" );
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance( 680, 300, Image.SCALE_DEFAULT );
        icon.setImage( newImage );

        int number = index;

        problemImage.setText( "Problem " + number );
        problemImage.setIcon( icon );
        problemImage.setVerticalTextPosition( JLabel.BOTTOM );
        problemImage.setHorizontalTextPosition( JLabel.CENTER );
    }


    private class TimerListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            timerCount++;
            int seconds = timerCount % 60;
            if ( seconds < 10 )
            {
                timerDisplay.setText( "Time: " + timerCount / 60 + ":0" + seconds );
            }
            else
            {
                timerDisplay.setText( "Time: " + timerCount / 60 + ":" + seconds );
            }
        }
    }


    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            // problem.reset();
            MainMenu mainMenu = new MainMenu( problem );
            frame.dispose();
        }
    }


    private class AButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            index++;
            updateScore( forward.peek().getAnswer() == 'A', timerCount );
            updateSolutionsPage();
        }
    }


    private class BButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            index++;
            updateScore( forward.peek().getAnswer() == 'B', timerCount );
            updateSolutionsPage();
        }
    }


    private class CButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            index++;
            updateScore( forward.peek().getAnswer() == 'C', timerCount );
            updateSolutionsPage();
        }
    }


    private class DButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            index++;
            updateScore( forward.peek().getAnswer() == 'D', timerCount );
            updateSolutionsPage();
        }
    }


    private class EButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            index++;
            updateScore( forward.peek().getAnswer() == 'E', timerCount );
            updateSolutionsPage();
        }
    }


    private class PrevButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            if ( !back.isEmpty() )
            {
                forward.push( back.pop() );
                updateProblem( forward.peek(), true);
            }
        }
    }


    private class NextButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            if ( index != 24 )
            {
                index++;
                update();
            }
        }
    }


    private class ViewButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            SolutionsMenu sol = new SolutionsMenu( problem, question.get( index ), 1 );
        }
    }


    /**
     * Method was taken from docs.oracle.com
     * 
     * @param path
     *            is the pathname of the image
     * @param description
     *            is the name of the image
     * @return ImageIcon, a fixed-size picture
     */
    protected ImageIcon createImageIcon( String path, String description )
    {
        java.net.URL imgURL = getClass().getResource( path );
        if ( imgURL != null )
        {
            return new ImageIcon( imgURL, description );
        }
        else
        {
            System.err.println( "Couldn't find file: " + path );
            return null;
        }
    }


    /**
     * Runs the GUI method.
     * 
     * @param args
     */
    public static void main( String[] args )
    {
        QQMenu hi = new QQMenu( null );
    }
}

// public class QQMenu
// {
// private JFrame frame;
//
// private ProblemDatabase problem;
//
// private Problem question;
//
// private int points;
//
//
// public QQMenu( ProblemDatabase problem )
// {
// this.problem = problem;
//
// points = 0;
//
// question = problem.giveRandProblem();
//
// JButton back = new JButton( "Back" );
// back.setBounds( 10, 10, 50, 50 );
// back.addActionListener( new BackButtonListener() );
//
// JButton choice1 = new JButton( "A" );
// choice1.setBounds( 150, 500, 100, 50 );
// choice1.addActionListener( new AButtonListener() );
//
// JButton choice2 = new JButton( "B" );
// choice2.setBounds(250, 500, 100, 50 );
// choice2.addActionListener( new BButtonListener() );
//
// JButton choice3 = new JButton( "C" );
// choice3.setBounds( 350, 500, 100, 50 );
// choice3.addActionListener( new CButtonListener() );
//
// JButton choice4 = new JButton( "D" );
// choice4.setBounds( 450, 500, 100, 50 );
// choice4.addActionListener( new DButtonListener() );
//
// JButton choice5 = new JButton( "E" );
// choice5.setBounds( 550, 500, 100, 50 );
// choice5.addActionListener( new EButtonListener() );
//
//
// frame = new JFrame( "Quick Quiz Menu" );
// frame.setDefaultCloseOperation( 0 );
// Container c = frame.getContentPane();
// frame.setLayout( null );
// frame.setBounds( 0, 0, 800, 600 );
//
// c.add( back );
// c.add( choice1 );
// c.add( choice2 );
// c.add( choice3 );
// c.add( choice4 );
// c.add( choice5 );
//
// frame.setResizable( false );
// frame.setVisible( true );
// }
//
//
// private class BackButtonListener implements ActionListener
// {
// public void actionPerformed( ActionEvent e )
// {
// MainMenu mainMenu = new MainMenu( problem );
// frame.dispose();
// }
// }
//
// private class AButtonListener implements ActionListener
// {
// public void actionPerformed( ActionEvent e )
// {
// if (question.getAnswer() == 'A')
// {
//
// }
// else
// {
//
// }
//
// SolutionsMenu solution = new SolutionsMenu(problem, question, 0);
// }
// }
//
// private class BButtonListener implements ActionListener
// {
// public void actionPerformed( ActionEvent e )
// {
// if (question.getAnswer() == 'B')
// {
//
// }
// else
// {
//
// }
//
// SolutionsMenu solution = new SolutionsMenu(problem, question, 0);
// }
// }
//
// private class CButtonListener implements ActionListener
// {
// public void actionPerformed( ActionEvent e )
// {
// if (question.getAnswer() == 'C')
// {
//
// }
// else
// {
//
// }
//
// SolutionsMenu solution = new SolutionsMenu(problem, question, 0);
// }
// }
//
// private class DButtonListener implements ActionListener
// {
// public void actionPerformed( ActionEvent e )
// {
// if (question.getAnswer() == 'D')
// {
//
// }
// else
// {
//
// }
//
// SolutionsMenu solution = new SolutionsMenu(problem, question, 0);
// }
// }
//
// private class EButtonListener implements ActionListener
// {
// public void actionPerformed( ActionEvent e )
// {
// if (question.getAnswer() == 'E')
// {
//
// }
// else
// {
//
// }
//
// SolutionsMenu solution = new SolutionsMenu(problem, question, 0);
// }
// }
// }