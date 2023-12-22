package UI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import BLL.AdministratorService;
import BLL.TopAdminService;
import BLL.VolunteerService;
import Entity.Administrator;
import Entity.TopAdmin;
import Entity.Volunteer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class loginUIforAll extends JFrame {

	private JPanel contentPane;
	private JTextField accountName;
	JRadioButton rdbtnAdmin ;
	JRadioButton rdbtnVolunteer;
	JRadioButton rdbtnTopadmin;
	private JPasswordField txtPwd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginUIforAll frame = new loginUIforAll();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public loginUIforAll() {
		this.setTitle("志愿者管理系统登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setFont(new Font("微軟正黑體 Light", Font.BOLD, 20));
		lblNewLabel.setBounds(114, 10, 161, 50);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(48, 72, 323, 274);
		contentPane.add(panel);
		panel.setLayout(null);

		accountName = new JTextField();
		accountName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		accountName.setBounds(53, 61, 194, 28);
		panel.add(accountName);
		accountName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u8D26\u6237");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(53, 34, 72, 28);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(53, 99, 58, 19);
		panel.add(lblNewLabel_2);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 200, 303, 53);
		panel.add(panel_1);
		panel_1.setLayout(null);
		rdbtnVolunteer = new JRadioButton("\u5FD7\u613F\u8005");
		rdbtnVolunteer.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		rdbtnVolunteer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnVolunteer.setSelected(rdbtnVolunteer.isSelected());
				rdbtnAdmin.setSelected(!rdbtnVolunteer.isSelected());
				rdbtnTopadmin.setSelected(!rdbtnVolunteer.isSelected());
			}
		});
		rdbtnVolunteer.setSelected(true);
		rdbtnVolunteer.setBounds(6, 17, 70, 27);
		panel_1.add(rdbtnVolunteer);

		rdbtnAdmin = new JRadioButton("\u7BA1\u7406\u5458");
		rdbtnAdmin.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAdmin.setSelected(rdbtnAdmin.isSelected());
				rdbtnVolunteer.setSelected(!rdbtnAdmin.isSelected());
				rdbtnTopadmin.setSelected(!rdbtnAdmin.isSelected());
			}
		});
		rdbtnAdmin.setBounds(105, 15, 70, 30);
		panel_1.add(rdbtnAdmin);

		rdbtnTopadmin = new JRadioButton("\u6700\u9AD8\u7BA1\u7406\u5458");
		rdbtnTopadmin.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		rdbtnTopadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAdmin.setSelected(!rdbtnTopadmin.isSelected());
				rdbtnVolunteer.setSelected(!rdbtnTopadmin.isSelected());
				rdbtnTopadmin.setSelected(rdbtnTopadmin.isSelected());
			}
		});
		rdbtnTopadmin.setBounds(200, 17, 97, 27);
		panel_1.add(rdbtnTopadmin);

		txtPwd = new JPasswordField();
		txtPwd.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		txtPwd.setBounds(53, 128, 194, 28);
		panel.add(txtPwd);

		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userID=accountName.getText();
				String userPwd=String.valueOf(txtPwd.getPassword());

				login(userID,userPwd);
			}
		});
		btnLogin.setFont(new Font("宋体", Font.PLAIN, 16));
		btnLogin.setBounds(47, 402, 97, 35);
		contentPane.add(btnLogin);

		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 16));
		btnCancel.setBounds(154, 402, 97, 35);
		contentPane.add(btnCancel);

		JButton btnRegister = new JButton("\u6CE8\u518C");
		btnRegister.setFont(new Font("宋体", Font.PLAIN, 15));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register();
			}
		});
		btnRegister.setBounds(274, 402, 97, 35);
		contentPane.add(btnRegister);
	}
	private void login(String userID,String userPwd) {
		if (rdbtnAdmin.isSelected()) {
			adminLogin(userID, userPwd);
		} else if (rdbtnVolunteer.isSelected()) {
			//选择了学生
			volunteerLogin(userID, userPwd);
		} else if (rdbtnTopadmin.isSelected()) {
			TopadminLogin(userID, userPwd);
		}
	}
	private void volunteerLogin(String userID,String userPwd)

	{

		VolunteerService aService=new VolunteerService();

		Volunteer curVolunteer=aService.login(userID, userPwd);


		if (curVolunteer==null) {
			JOptionPane.showMessageDialog(null, "登陆失败!"+aService.getMessage(),"提示信息",1);
			return ;
		}
		//登录成功
		new mainWin(curVolunteer);
		this.dispose();
	}
	private void adminLogin(String userID,String userPwd)
	{

		AdministratorService aService=new AdministratorService();

		Administrator curAdmin=aService.login(userID,userPwd);
		//判定登录是否成功
		if (curAdmin==null) {
			JOptionPane.showMessageDialog(null, "登陆失败!"+aService.getMessage(),"提示信息",1);
			return ;
		}
		//登录成功
		new mainWin(curAdmin);
		this.dispose();
	}
	private void TopadminLogin(String userID,String userPwd)
	{

		TopAdminService topAdminService=new TopAdminService();
		TopAdmin curTopAdmin=topAdminService.login(userID,userPwd);

		if (curTopAdmin==null) {
			JOptionPane.showMessageDialog(null, "登陆失败!"+topAdminService.getMessage(),"提示信息",1);
			return ;
		}
		new mainWin(curTopAdmin);
		this.dispose();
	}
	private void Register()
	{
		if(rdbtnAdmin.isSelected()) {

			JOptionPane.showMessageDialog(null,"管理员不能注册！","提示",1);
		}
		else if(rdbtnVolunteer.isSelected()) {
			new registerUIforVol(this);
			this.setVisible(false);
		}else if(rdbtnTopadmin.isSelected()){
			JOptionPane.showMessageDialog(null,"最高管理员不能注册！","提示",1);
		}
	}
}