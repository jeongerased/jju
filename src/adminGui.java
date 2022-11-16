import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class adminGui extends JFrame implements ActionListener {
	JTextField searchText;
	JButton searchBtn;
	JButton insertBtn;
	JButton updateBtn;
	JButton deleteBtn;
	JButton shopBtn;
	
	public adminGui(ArrayList<User> list){
		setTitle("관리자");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentpane = getContentPane();
		contentpane.setLayout(null);
		contentpane.setBackground(Color.LIGHT_GRAY);
		
		//검색
		JLabel searchLabel=new JLabel("이름 :");
		searchLabel.setBounds(70, 40, 67, 25);
		searchLabel.setFont(new Font("", Font.BOLD, 15));
		contentpane.add(searchLabel);
		searchText=new JTextField();
		searchText.setBounds(130, 40, 280, 25);
		contentpane.add(searchText);
		searchBtn=new JButton("검색");
		searchBtn.addActionListener(this);
		searchBtn.setBounds(430, 40, 67, 25);
		contentpane.add(searchBtn);
		
		//등록,수정,삭제 버튼, 상점 버튼
		insertBtn = new JButton("등록");
		insertBtn.addActionListener(this);
		insertBtn.setBounds(650, 180, 110, 30);
		contentpane.add(insertBtn);
		updateBtn = new JButton("수정");
		updateBtn.addActionListener(this);
		updateBtn.setBounds(650, 240, 110, 30);
		contentpane.add(updateBtn);
		deleteBtn = new JButton("삭제");
		deleteBtn.addActionListener(this);
		deleteBtn.setBounds(650, 300, 110, 30);
		contentpane.add(deleteBtn);
		
		shopBtn = new JButton("상점");
		shopBtn.addActionListener(this);
		shopBtn.setBounds(650, 400, 110, 30);
		contentpane.add(shopBtn);
		
		//테이블
		
		String column[]= {"사원번호","사원이름","부서","직급","반차","상벌점","포인트"};
		String[][] s=new String[100][7];//= {{null,null,null,null,null,null,null}};
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<7;j++) {
				if(j==0) s[i][j]=list.get(i).getId()+"";
				if(j==1) s[i][j]=list.get(i).getName();
				if(j==2) s[i][j]=list.get(i).getDepart();
				if(j==3) s[i][j]=list.get(i).getRank();
				if(j==4) s[i][j]=list.get(i).getHalfway()+"";
				if(j==5) s[i][j]=list.get(i).getReward()+"";
				if(j==6) s[i][j]=list.get(i).getPoint()+"";
			}
		}
		
		JTable users=new JTable(s,column);		
		users.getTableHeader().setReorderingAllowed(false);
		users.getTableHeader().setResizingAllowed(false);
		JScrollPane scroll=new JScrollPane(users);
		scroll.setBounds(40, 90, 580, 440);
		contentpane.add(scroll);
		
		setSize(800,600);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==insertBtn) {
			insertBtn.setText("test");
		}
		else if(e.getSource()==updateBtn) {
			updateBtn.setText("test");
		}
		else if(e.getSource()==deleteBtn) {
			deleteBtn.setText("test");
		}
		else if(e.getSource()==shopBtn) {
			new AdminShop();
		}
		else if(e.getSource()==searchBtn) {
			searchBtn.setText("test");
		}
	}
}
