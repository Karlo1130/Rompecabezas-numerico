import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import java.util.Random;
import java.util.HashSet;

public class Main extends JFrame {

	private JPanel contentPane;
	
	private JButton[][] botones;
	private JButton temporal;
	
	private int numeroSeleccion = 1;
	private int primerBotonX, primerBotonY;
	
	private int[] numerosAleatorios = new int[16];
	
	private Lista lista = new Lista(4, 4);
	private Lista listaComparativa = new Lista(4, 4);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 616);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 255));
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 4));
		
		botones = new JButton[4][4];
		
		ordenAleatorio();
		
		int aux = 0;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				botones[j][i] = new JButton(""+numerosAleatorios[aux++]);
				botones[j][i].setBackground(Color.white);
				
				if(botones[j][i].getText().equals("16")) {
					botones[j][i].setText("");
				}
				
				agregarAccion(botones[j][i], j, i);
				panel.add(botones[j][i]);
			}
		}
		
		lista.CrearLista();
		lista.desplegarLista();
		
		listaComparativa.CrearListaComparativa();
	}
	
	public class Nodo {
		int dato;
		Nodo Arriba, Abajo, Derecha, Izquierda;
		
		public Nodo()
		{
			 	dato=0;
			 	Izquierda= null;
				Derecha= null;
				Arriba= null;
				Abajo= null;
		}
	}
	
	public class Lista {
		Nodo   Head, P, Q, R;
		private int renglones, columnas;
		
		public Lista(int renglones, int columnas)
		{
			this.renglones=renglones;
			this.columnas=columnas;
			Head = null;
		}
		
		public void CrearLista(){
			int aux = 0;
			for (int X = 1; X <= renglones; X++){
				for (int Y = 1; Y <= columnas; Y++){
					P = new Nodo();
					P.dato = numerosAleatorios[aux++];
					
					if (Y == 1){
						if (Head == null){
							Head = P;
						}
						Q = P;
					}else{
						P.Izquierda = Q;
						Q.Derecha= P;
						Q = P;
					}
					
					if (X == 1){
						Q = P;
					}else{
						P.Arriba = R;
						R.Abajo = P;
						R = R.Derecha;
					}
				}// for columnas
				R = Head;
				while (R.Abajo != null){
					R = R.Abajo;
				}
			}// for renglones
			
		}
		
		public void CrearListaComparativa(){
			int aux = 1;
			for (int X = 1; X <= renglones; X++){
				for (int Y = 1; Y <= columnas; Y++){
					P = new Nodo();
					P.dato = aux++;
					
					if (Y == 1){
						if (Head == null){
							Head = P;
						}
						Q = P;
					}else{
						P.Izquierda = Q;
						Q.Derecha= P;
						Q = P;
					}
					
					if (X == 1){
						Q = P;
					}else{
						P.Arriba = R;
						R.Abajo = P;
						R = R.Derecha;
					}
				}// for columnas
				R = Head;
				while (R.Abajo != null){
					R = R.Abajo;
				}
			}// for renglones
			
		}
				
		public boolean compararListas(Lista listaComparativa) {
			
			int contador = 0;
			
			if (Head != null){
				Q = Head;
				listaComparativa.Q = listaComparativa.Head;
				while( Q != null)//renglon
				{
					P = Q;
					listaComparativa.P = listaComparativa.Q;
					while(P != null)//columna
					{
						if(P.dato == listaComparativa.P.dato) {
							contador++;
						}
						
						P = P.Derecha;
						
						listaComparativa.P = listaComparativa.P.Derecha;
					}
					Q = Q.Abajo;
					
					listaComparativa.Q = listaComparativa.Q.Abajo;
				}
			}
			
			if(contador == 16) {
				return true;
			}else {
				return false;
			}
			
		}
		
		public void desplegarLista(){
			if (Head != null){
				Q = Head;
				while( Q != null)//renglon
				{
					P = Q;
					while(P != null)//columna
					{
						System.out.print(" "+P.dato);
						P = P.Derecha;
					}
					Q = Q.Abajo;
					System.out.print("\n");
				}
				System.out.print("\n");
			}
		}
		
		public boolean cambiarLugar(int x, int y, int x2, int y2) {
			P = Head;
			int contY = 0;
			while(contY != y) {
				P = P.Abajo;
				contY++;
			}
			
			int contX = 0;
			while(contX != x) {
				P = P.Derecha;
				contX++;
			}
			
			Q = Head;
			int contY2 = 0;
			while(contY2 != y2) {
				Q = Q.Abajo;
				contY2++;
			}
			
			int contX2 = 0;
			while(contX2 != x2) {
				Q = Q.Derecha;
				contX2++;
			}
			
			System.out.println(Q.dato);
			
			if(Q.dato == 16 &&
					(P.Derecha != null && P.Derecha.dato == Q.dato ||
					P.Abajo != null && P.Abajo.dato == Q.dato ||
					P.Izquierda != null && P.Izquierda.dato == Q.dato ||
					P.Arriba != null && P.Arriba.dato == Q.dato)) {
				
				System.out.println("si1");
				
				int aux = P.dato;
				
				P.dato = 16;
				Q.dato = aux;
				
				return true;
			}else if(P.dato == 16 &&
					(P.Derecha != null && P.Derecha.dato == Q.dato ||
					P.Abajo != null && P.Abajo.dato == Q.dato ||
					P.Izquierda != null && P.Izquierda.dato == Q.dato ||
					P.Arriba != null && P.Arriba.dato == Q.dato)) {
				System.out.println("si2");
				
				int aux = P.dato;
				
				P.dato = Q.dato;
				Q.dato = aux;
				return true;
			}else {
				System.out.println("no");
				return false;
			}
//			
		}
	}
	
	void agregarAccion(final JButton boton,  int x,  int y) {
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

            	
            	if(numeroSeleccion == 1) {
            		primerBotonX = x;
            		primerBotonY = y;
            		
            		temporal = boton;
            		boton.setBackground(Color.green);
            	}else {

            		if(lista.cambiarLugar(primerBotonX, primerBotonY,
            							  x, y)) {
            			String aux = botones[primerBotonX][primerBotonY].getText();
            			
            			botones[primerBotonX][primerBotonY].setText(botones[x][y].getText());
            			botones[x][y].setText(aux);
            		}
            		temporal.setBackground(Color.white);
            		lista.desplegarLista();
            	}
            	
            	if(lista.compararListas(listaComparativa)) {
            		System.out.println("ganas!!!!!!!!!!!!!");
                	JOptionPane.showMessageDialog(contentPane, "El Jugador gana","Victoria", JOptionPane.INFORMATION_MESSAGE);
            	}
            	
            	cambiarSeleccion();

            }
        });
	}
	
	void cambiarSeleccion(){
		
        if(numeroSeleccion == 1){
            numeroSeleccion = 2;
        }else{
            numeroSeleccion = 1;
        }
    }
	
	void ordenAleatorio() {
		Random rand = new Random();
		HashSet<Integer> set = new HashSet<Integer>();
		
		int aux = 1;
		
		for (int i = 0; i < 16; i++) {
			set.add(aux++);
		}
		
		// Convertir el conjunto en un array y mezclar los elementos de manera aleatoria
		Integer[] nums = set.toArray(new Integer[0]);
		for (int i = nums.length - 1; i > 0; i--) {
			int j = rand.nextInt(i + 1);
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}

		int aux2 = 0;
		
		// Imprimir los números en el orden aleatorio generado
		for (int num : nums) {
			numerosAleatorios[aux2++] = num;
		}
		// pone los numeros en orden
//		int aux2 = 0;
//		
//		for (int i = 1; i <= 16; i++) {
//			numerosAleatorios[aux2++] = i;
//		}
	}
}
