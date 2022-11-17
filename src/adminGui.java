import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class adminGui extends JFrame implements ActionListener {
	JTextField searchText;
	JButton searchBtn;
	JButton insertBtn;
	JButton updateBtn;
	JButton deleteBtn;
	JButton shopBtn;
	JButton refreshBtn;
	JTable users;
	JScrollPane scroll;
	
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
		searchBtn.setBounds(430, 40, 90, 25);
		contentpane.add(searchBtn);
		refreshBtn =new JButton("새로고침");
		refreshBtn.addActionListener(this);
		refreshBtn.setBounds(530, 40, 90, 25);
		contentpane.add(refreshBtn);
		
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
			s[i][0]=list.get(i).getId()+"";
			s[i][1]=list.get(i).getName();
			s[i][2]=list.get(i).getDepart();
			s[i][3]=list.get(i).getRank();
			s[i][4]=list.get(i).getHalfway()+"";
			s[i][5]=list.get(i).getReward()+"";
			s[i][6]=list.get(i).getPoint()+"";
		}
		
		users=new JTable(s,column);	
		users.getTableHeader().setReorderingAllowed(false);
		users.getTableHeader().setResizingAllowed(false);
		scroll=new JScrollPane(users);
		scroll.setBounds(40, 90, 580, 440);
		contentpane.add(scroll);
		
		setSize(800,600);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==insertBtn) {
			new RegistGui();
		}
		else if(e.getSource()==updateBtn) {
			new ModifyGui();
		}
		else if(e.getSource()==deleteBtn) {
			String idstr=JOptionPane.showInputDialog("삭제할 사원번호를 입력하세요.");
			if(idstr!=null) {
				DBA db=new DBA();
				int id;
				id=Integer.parseInt(idstr);
				db.deleteData(id);
				JOptionPane.showConfirmDialog(null, "성공적으로 삭제되었습니다.","확인",JOptionPane.YES_OPTION);
			}
		}
		else if(e.getSource()==shopBtn) {
			new AdminShop();
		}
		else if(e.getSource()==searchBtn) {
			if(searchText.getText().equals("")) {				
			}
			else {
				DBA db=new DBA();
				ArrayList<User> list=new ArrayList<>();
				scroll.setVisible(false);
				db.selectNameData(list, searchText.getText());
				
				String column[]= {"사원번호","사원이름","부서","직급","반차","상벌점","포인트"};
				String[][] s=new String[100][7];
				for(int i=0;i<list.size();i++) {
					s[i][0]=list.get(i).getId()+"";
					s[i][1]=list.get(i).getName();
					s[i][2]=list.get(i).getDepart();
					s[i][3]=list.get(i).getRank();
					s[i][4]=list.get(i).getHalfway()+"";
					s[i][5]=list.get(i).getReward()+"";
					s[i][6]=list.get(i).getPoint()+"";
				}
				
				users=new JTable(s,column);	
				users.getTableHeader().setReorderingAllowed(false);
				users.getTableHeader().setResizingAllowed(false);
				scroll=new JScrollPane(users);
				scroll.setBounds(40, 90, 580, 440);
				getContentPane().add(scroll);
			}
		}
		else if(e.getSource()==refreshBtn) {
			searchText.setText("");
			DBA db=new DBA();
			ArrayList<User> list=new ArrayList<>();
			scroll.setVisible(false);
			db.selectAllData(list);
			
			String column[]= {"사원번호","사원이름","부서","직급","반차","상벌점","포인트"};
			String[][] s=new String[100][7];
			for(int i=0;i<list.size();i++) {
				s[i][0]=list.get(i).getId()+"";
				s[i][1]=list.get(i).getName();
				s[i][2]=list.get(i).getDepart();
				s[i][3]=list.get(i).getRank();
				s[i][4]=list.get(i).getHalfway()+"";
				s[i][5]=list.get(i).getReward()+"";
				s[i][6]=list.get(i).getPoint()+"";
			}
			
			users=new JTable(s,column);	
			users.getTableHeader().setReorderingAllowed(false);
			users.getTableHeader().setResizingAllowed(false);
			scroll=new JScrollPane(users);
			scroll.setBounds(40, 90, 580, 440);
			getContentPane().add(scroll);
		}
	}
}
