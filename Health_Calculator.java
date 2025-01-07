import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Health_Calculator 
{
    JFrame f;

    public static void main() 
    {
        JFrame f = new JFrame("Health Calculator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(700, 550);
        f.setLayout(new GridLayout(10, 2, 10, 10));
        f.getContentPane().setBackground(new Color(210, 255, 210));
        
        JLabel gl = new JLabel("Choose gender:");
        String[] g = {"Female", "Male", "Others"};
        JComboBox<String> gb = new JComboBox<>(g);
        JLabel ucl = new JLabel("Preference of unit system:");
        JLabel al= new JLabel("Add your Age:");
        JTextField af= new JTextField();
        String[] uc = {"kg-cm", "pound-inch"};
        JComboBox<String> ucb = new JComboBox<>(uc);
        JLabel wtl = new JLabel("Weight:");
        JTextField wtf = new JTextField();
        JLabel htl = new JLabel("Height:");
        JTextField htf = new JTextField();
       
        JButton calculateButton = new JButton("Calculate ");
      
        JLabel bmirl= new JLabel("Body Mass Index: ", JLabel.CENTER);
        JLabel bfprl= new JLabel("Body Fat Percentage: ", JLabel.CENTER);
        JLabel bmrrl= new JLabel("Body Metabolic Rate:", JLabel.CENTER);
        JLabel iwtl= new JLabel("Ideal Weight Range: ", JLabel.CENTER);

        f.add(gl);
        f.add(gb);
        f.add(new JLabel());
        f.add(al);
        f.add(af);
        f.add(new JLabel());
        f.add(ucl);
        f.add(ucb);
        f.add(new JLabel());
        f.add(wtl);
        f.add(wtf);
        f.add(new JLabel());
        f.add(htl);
        f.add(htf);
        f.add(new JLabel()); 
        f.add(calculateButton);
        f.add(new JLabel()); 
        f.add(bmirl);
        f.add(bfprl);
        f.add(bmrrl);
        f.add(iwtl);
        
        calculateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String uc= (String) ucb.getSelectedItem();
                    String g= (String) gb.getSelectedItem();
                    double wt= Double.parseDouble(wtf.getText());
                    double ht= Double.parseDouble(htf.getText());
                    int a= Integer.parseInt(af.getText());
                    double bmi,bfp,bmr,minwt,maxwt;
                    if ("kg-cm".equals(uc)) 
                    {
                        ht=ht/100.0;
                        bmi= wt/Math.pow(ht, 2);
                        minwt= 18.5*Math.pow(ht,2);
                        maxwt= 24.9*Math.pow(ht,2);
                        iwtl.setText(String.format("Ideal Weight: %.2f - %.2f %s", minwt, maxwt, "kg"));
                    } else
                    {
                        bmi= (wt * 703) / Math.pow(ht, 2);
                        minwt= 18.5*Math.pow(ht, 2)/703;
                        maxwt= 24.9*Math.pow(ht,2)/703;
                        iwtl.setText(String.format("Ideal Weight: %.2f - %.2f %s", minwt, maxwt, "pound"));
                    }
                    String type;
                    if (bmi < 18.5) 
                    {
                        type="Underweight";
                    } 
                    else if (bmi >= 18.5 && bmi <= 24.9)
                    {
                        type="Optimum range";
                    } 
                    else if (bmi >= 25.0 && bmi <= 29.9) 
                    {
                        type="Overweight";
                    } 
                    else if (bmi >= 30.0 && bmi <= 34.9) 
                    {
                        type="Class I obese";
                    } 
                    else if (bmi >= 35.0 && bmi <= 39.9)
                    {
                        type="Class II obese";
                    } 
                    else
                    {
                        type="Class III obese";
                    }
                    bmirl.setText(String.format("Body Mass Index: %.2f (%s)", bmi, type));
                    
                    int gf = ("Male".compareTo(g)==0) ? 1 : 0;
                    bfp = 1.20 * bmi + 0.23 * a - 10.8 * gf - 5.4;
                    bfprl.setText(String.format("Body Fat Percentage: %.2f%%", bfp));
                    
                    if (gf==1) 
                    {
                        bmr = (88.4+(13.4*wt)+(4.8*ht*100))-(5.68*a); 
                    } else
                    {
                        bmr = 447.6+(9.25*wt)+(3.1*ht*100)-(4.33*a);
                    }
                    
                    bmrrl.setText(String.format("Basic Metabolic Rate: %.2f cal/day", bmr));
                } catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(f,"Please enter valid numeric values!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        f.setVisible(true);
    }
}
