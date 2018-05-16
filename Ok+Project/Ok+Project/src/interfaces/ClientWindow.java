package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import user.Customer;
import Obj.Product;
import Obj.ProductContainer;
import Obj.ProductList;

public class ClientWindow extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pNord; // welcome
	private JPanel pSud; // tout ce qui ce trouve au centre
	private JPanel pSudOuest; // liste items
	private JPanel pSudEst; // log in / sign in
	private JScrollPane scrollPane;
	private JPanel itemsPanel;
	private JPanel cartPanel;
	private JPanel fp;
	private JButton btnLogout;
	private JButton btnAddCart;
	private JButton btnAddMoney;
	private JTextField txtMoney;
	private Product currentProd;
	private Customer customer;
	
	private static final Color blueDarkColor = new Color(127, 143, 166);
	private static final Color blueGrayColor = new Color(200,200,220);
	
	public ClientWindow(Customer cus) {
		super();
		this.customer = cus;
		build();//On initialise notre fenï¿½tre
		System.out.println("Welcom "+customer.getFirstN() + " " + customer.getLastN());
	}
	
	private void build(){
		/** Config principal panel **/
		setBackground(new Color(200,200,250));
		setLayout(new BorderLayout());
		
		setLayout(new BorderLayout());
		setTitle("OK+"); //window title
		setSize(800,500); //On donne une taille ï¿½ notre fenï¿½tre
		setLocationRelativeTo(null); //On centre la fenï¿½tre sur l'ï¿½cran
		setResizable(false); //On interdit la redimensionnement de la fenï¿½tre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit ï¿½ l'application de se	fermer lors du clic sur la croix
		
		designPanel();
		
	}
	
	
	void designPanel(){
		
		ProductList.getInstance().getList().addListSelectionListener(new ListSelectionListener(	) {
			
			@Override
			 public void valueChanged(ListSelectionEvent event) {
            	if (!event.getValueIsAdjusting()){
            		Object result =  ProductList.getInstance().getList().getSelectedValue();
            		if (result instanceof Product) {
            		    currentProd =  (Product)result;
            		    System.out.println("you are currently selecting : "+ currentProd.getName());
            		}
                    
                }
            }
		});
		fp = new JPanel();
		
		/* design buttons and JTextFields */
		btnLogout = new JButton("Log Out");
		btnLogout.setBounds(450, 30, 130, 30);
		
		btnAddCart = new JButton("Add Item");
		btnAddCart.setBounds(30, 260, 130, 30);
		
		btnAddCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
                customer.addCart(currentProd);
				updateCartPanel();
			}
			
		});
		
		btnAddMoney = new JButton("Add Money");
		btnAddMoney.setBounds(30, 300, 130, 30);
		
		
		
		txtMoney = new JTextField();
		txtMoney.setBounds(180, 300, 130, 30);
		
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				PrincipalWindow pw = new PrincipalWindow();
				pw.setVisible(true);
				dispose();
				
			}
		});
		
		btnAddMoney.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(Integer.parseInt(txtMoney.getText())>0){
					customer.addMoney(Integer.parseInt(txtMoney.getText()));
					System.out.println("You add " + txtMoney.getText());
					System.out.println("Now you have : " + customer.getBalance());
				}else
					JOptionPane.showMessageDialog(null, "Invalid amount");
			}
			
		});
		
		/* panel Nord - Welcome */
		pNord = new JPanel();
		pNord.setLayout(null);
		pNord.setBackground(blueGrayColor);
		pNord.setOpaque(true);
		pNord.setSize(800, 100);
		pNord.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, blueDarkColor));
		
		/* label welcom */
		JLabel lblWelcom = new JLabel("Welcom "+this.customer.getFirstN()+" "+this.customer.getLastN());
		lblWelcom.setBounds(40, 10, 500, 40);
		
		pNord.add(lblWelcom);
		pNord.add(btnLogout);
		
		/* Config pSud */
		
		pSud = new JPanel();
		pSud.setBackground(blueDarkColor);
		pSud.setBorder(BorderFactory.createMatteBorder(0, 10, 10, 10, blueDarkColor));
		pSud.setBackground(blueGrayColor);
		pSud.setLayout(new GridLayout(1,2));
		pSud.setBounds(0, 100, 795, 370);

		/* Panel à gauche */
		//  haut , gauche ,  bas, droit 
		pSudOuest = new JPanel();
		pSudOuest.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, blueDarkColor));
		pSudOuest.setBackground(blueGrayColor);
		pSudOuest.setLayout(null);
	
		
		/* Panel à droite */
		pSudEst = new JPanel();
		pSudEst.setBackground(blueGrayColor);
		pSudEst.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, blueDarkColor));
		pSudEst.setLayout(null);

		/* panel pour la liste des items */
		itemsPanel = new JPanel();
		itemsPanel.setLayout(new BorderLayout());
		itemsPanel.setBounds(35, 30, 300, 200); //taille du panel qui contient la liste
		
		/* panel pour la liste des items dans cart */
		cartPanel = new JPanel();
		cartPanel.setLayout(new BorderLayout());
		cartPanel.setBounds(35, 30, 300, 200); //taille du panel qui contient la liste
		
		pSudOuest.add(itemsPanel);
		pSudOuest.add(btnAddCart);
		pSudOuest.add(btnAddMoney);
		pSudOuest.add(txtMoney);
		
		pSudEst.add(cartPanel);
		
		/* Ajout des elements du panel sud */
		pSud.add(pSudOuest);
		pSud.add(pSudEst);

		displayListItem();
		displayListCart();
		
		/* Ajout des elements du panel principal */
		fp.setLayout(null); 
		fp.add(pNord);
		fp.add(pSud);
		
		setContentPane(fp); // ajout du panel principal à la window
	}
	
	public void updateItemPanel() {
		itemsPanel.validate();
		itemsPanel.repaint();
	}
	public void updateCartPanel() {
		cartPanel.validate();
		cartPanel.repaint();
	}
	
	public void displayListItem(){
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(ProductList.getInstance().getList());
		itemsPanel.add(scrollPane);
		updateItemPanel();
	}

	public void displayListCart(){
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(customer.getJList());
		cartPanel.add(scrollPane);
		updateCartPanel();
	}
}
