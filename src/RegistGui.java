import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class ImagePanel2 extends JPanel {
    Image image;
    Toolkit toolkit = getToolkit();


    void setPath(String path) {
        image = toolkit.getImage(path); //이미지 경로 저장
    }

    public void paint(Graphics g) { //이미지 불러오기
        g.clearRect(0, 0, getWidth(), getHeight());
        if (image != null)
            g.drawImage(image, 0, 0, this);
    }
}

public class RegistGui extends JFrame {
	String imgaddr=null;
   private JPanel contentPane;
   private JTextField idTextField;
   private JTextField nameTextField;
   private JTextField departTextField;
   private JTextField rankTextField;
   private JTextField rewordTextField;
   private JTextField halfwayTextField;

   public static void main(String[] args) {
	   new RegistGui();
   }

   public RegistGui() {
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 679, 440);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      
      
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("관리자");
      lblNewLabel.setBounds(274, 20, 99, 36);
      lblNewLabel.setForeground(Color.BLUE);
      lblNewLabel.setFont(new Font("궁서체", Font.BOLD | Font.ITALIC, 30));
      contentPane.add(lblNewLabel);
      
      JLabel idLabel = new JLabel("사번");
      idLabel.setBounds(216, 86, 50, 15);
      contentPane.add(idLabel);
      
      JLabel nameLabel = new JLabel("이름");
      nameLabel.setBounds(216, 131, 50, 15);
      contentPane.add(nameLabel);
      
      JLabel departLabel = new JLabel("부서");
      departLabel.setBounds(216, 179, 50, 15);
      contentPane.add(departLabel);
      
      JLabel rankLabel = new JLabel("직급");
      rankLabel.setBounds(216, 230, 50, 15);
      contentPane.add(rankLabel);
      
      JLabel halfwayLabel = new JLabel("반차");
      halfwayLabel.setBounds(216, 278, 80, 15);
      contentPane.add(halfwayLabel);
      
      JLabel rewordLabel = new JLabel("상벌점");
      rewordLabel.setBounds(206, 323, 50, 15);
      contentPane.add(rewordLabel);
      
      idTextField = new JTextField();
      idTextField.setBounds(257, 83, 360, 21);
      contentPane.add(idTextField);
      idTextField.setColumns(10);
      
      nameTextField = new JTextField();
      nameTextField.setBounds(257, 128, 360, 21);
      nameTextField.setColumns(10);
      contentPane.add(nameTextField);
      
      departTextField = new JTextField();
      departTextField.setBounds(257, 176, 360, 21);
      departTextField.setColumns(10);
      contentPane.add(departTextField);
      
      rankTextField = new JTextField();
      rankTextField.setBounds(257, 227, 360, 21);
      rankTextField.setColumns(10);
      contentPane.add(rankTextField);
      
      halfwayTextField = new JTextField();
      halfwayTextField.setBounds(257, 274, 360, 21);
      halfwayTextField.setColumns(10);
      contentPane.add(halfwayTextField);
      
      rewordTextField = new JTextField();
      rewordTextField.setBounds(257, 320, 360, 21);
      rewordTextField.setColumns(10);
      contentPane.add(rewordTextField);
      
      
      JLabel lblNewLabel_1 = new JLabel("");
      lblNewLabel_1.setBounds(27, 104, 156, 157);
      contentPane.add(lblNewLabel_1);
      javax.swing.ImageIcon icon = new javax.swing.ImageIcon(this.getClass().getResource("/employee.jpg")); //이미지 파일명
        lblNewLabel_1.setIcon(icon);
      
      JButton btnNewButton = new JButton("사진 선택\r\n");
      btnNewButton.setBounds(61, 279, 91, 30);
      contentPane.add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("등록");
      btnNewButton_1.setBounds(526, 361, 91, 30);
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btnNewButton_1) {
            	DBA db=new DBA();
            	int id, halfway, reword;
            	String name,depart,rank;
            	id=Integer.parseInt(idTextField.getText());
            	halfway=Integer.parseInt(halfwayTextField.getText());
            	reword=Integer.parseInt(rewordTextField.getText());
            	name=nameTextField.getText();
            	depart=departTextField.getText();
            	rank=rankTextField.getText();
            	db.insertData(id,name,depart,rank,halfway,reword,0,imgaddr);
               JOptionPane.showMessageDialog(null,"등록되었습니다"); //버튼1 클릭시 "등록되었습니다" 메세지창 출력
            }
            dispose();
         }
      });
      contentPane.add(btnNewButton_1);
      
      
      //------------------------------------------//
        Container contentPane = getContentPane();

        setLocation(100, 100);
        setPreferredSize(new Dimension(700, 450));


        FileDialog image = new FileDialog(this,"사진 선택", FileDialog.LOAD); //이미지 파일 읽기
        
        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setBounds(0, 0, 0, 0);
        contentPane.add(imagePanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 0, 0, 0);
        JTextField text = new JTextField(30);
        controlPanel.add(text);
        contentPane.add(controlPanel);
        
        btnNewButton.addActionListener(e -> {  //버튼을 클릭했을때 이미지 디렉토리 경로와 파일이름 가져오기
            image.setVisible(true);
            if (image.getFile() != null) {
                ImageIcon imageIcon = new ImageIcon(image.getDirectory() + image.getFile());
                lblNewLabel_1.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(156, 161, Image.SCALE_SMOOTH)));//getScaledInstance 를 사용하여 사진을 크기에 맞춰서 출력
                imgaddr=image.getDirectory() + image.getFile();
            }
            imagePanel.repaint(); //repaint 메소드 호출
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);	// 화면 중간 출력
   }
}