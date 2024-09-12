import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordForm extends JFrame {

    private JPanel topBanner, forgotPasswordPanel;
    private JTextField emailField, phoneField, otpField;
    private JRadioButton emailOption, phoneOption;
    private CardLayout cardLayout;
    private JPanel inputPanel;

    
    
    public ForgotPasswordForm() {
        // Window settings
        setTitle("FORGOT PASSWORD");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Top Banner
        topBanner = new JPanel(new BorderLayout());
        topBanner.setBackground(new Color(219, 251, 232));
        topBanner.setPreferredSize(new Dimension(600, 150));
        
        JLabel leftLogo = new JLabel(new ImageIcon("CSNLogo.png"));
        JLabel rightLogo = new JLabel(new ImageIcon("PQLogo.png"));
        
        JPanel bannerText = new JPanel();
        bannerText.setLayout(new BoxLayout(bannerText, BoxLayout.Y_AXIS));
        bannerText.setOpaque(false);
        
        JLabel title = new JLabel("PARAÑAQUE CITY CENTER FOR CHILDREN WITH SPECIAL NEEDS", JLabel.CENTER);
        title.setFont(new Font("Montserrat", Font.BOLD, 16));
        JLabel address = new JLabel("Address: Col. E. de Leon Street, Barangay Sto. Niño, Parañaque City", JLabel.CENTER);
        JLabel contactInfo = new JLabel("Email: csncenterparanaque@gmail.com | Contact No: +632-8706 6531", JLabel.CENTER);
        
        bannerText.add(title);
        bannerText.add(address);
        bannerText.add(contactInfo);
        
        topBanner.add(leftLogo, BorderLayout.WEST);
        topBanner.add(bannerText, BorderLayout.CENTER);
        topBanner.add(rightLogo, BorderLayout.EAST);
        
        add(topBanner, BorderLayout.NORTH);
        
        // Forgot Password Panel
        forgotPasswordPanel = new JPanel();
        forgotPasswordPanel.setLayout(new BoxLayout(forgotPasswordPanel, BoxLayout.Y_AXIS));
        forgotPasswordPanel.setPreferredSize(new Dimension(350, 250));
        forgotPasswordPanel.setBackground(Color.WHITE);
        forgotPasswordPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel forgotPasswordTitle = new JLabel("Forgot Password");
        forgotPasswordTitle.setFont(new Font("Montserrat", Font.BOLD, 20));
        forgotPasswordTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        forgotPasswordPanel.add(forgotPasswordTitle);
        
        // Radio buttons for selecting Email or Phone
        emailOption = new JRadioButton("Use Email", true);
        phoneOption = new JRadioButton("Use Phone Number");
        
        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(emailOption);
        optionGroup.add(phoneOption);
        
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        optionPanel.add(emailOption);
        optionPanel.add(phoneOption);
        
        forgotPasswordPanel.add(optionPanel);
        
        // Input fields for Email or Phone and OTP
        cardLayout = new CardLayout();
        inputPanel = new JPanel(cardLayout);
        
        // Email Input
        JPanel emailPanel = new JPanel(new BorderLayout());
        JLabel emailLabel = new JLabel("Enter your email:");
        emailField = new JTextField();
        emailPanel.add(emailLabel, BorderLayout.NORTH);
        emailPanel.add(emailField, BorderLayout.CENTER);
        
        // Phone Input
        JPanel phonePanel = new JPanel(new BorderLayout());
        JLabel phoneLabel = new JLabel("Enter your phone number:");
        phoneField = new JTextField();
        phonePanel.add(phoneLabel, BorderLayout.NORTH);
        phonePanel.add(phoneField, BorderLayout.CENTER);
        
        inputPanel.add(emailPanel, "email");
        inputPanel.add(phonePanel, "phone");
        
        forgotPasswordPanel.add(inputPanel);
        
        // OTP Input
        JPanel otpPanel = new JPanel(new BorderLayout());
        JLabel otpLabel = new JLabel("Enter the verification code:");
        otpField = new JTextField();
        otpPanel.add(otpLabel, BorderLayout.NORTH);
        otpPanel.add(otpField, BorderLayout.CENTER);
        
        forgotPasswordPanel.add(otpPanel);
        
        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(40, 167, 69));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Montserrat", Font.PLAIN, 16));
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        forgotPasswordPanel.add(submitButton);
        
        // Add action listeners for the radio buttons
        emailOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(inputPanel, "email");
            }
        });

        phoneOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(inputPanel, "phone");
            }
        });
        
        add(forgotPasswordPanel, BorderLayout.CENTER);
        
        // Submit button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = emailOption.isSelected() ? "Email" : "Phone";
                String otp = otpField.getText();
                
                if (selectedOption.equals("Email")) {
                    String email = emailField.getText();
                    JOptionPane.showMessageDialog(null, "Email: " + email + "\nOTP: " + otp, "Submitted", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String phone = phoneField.getText();
                    JOptionPane.showMessageDialog(null, "Phone: " + phone + "\nOTP: " + otp, "Submitted", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ForgotPasswordForm().setVisible(true);
            }
        });
    }
}
