package net.joaosilva.bst;

import net.joaosilva.bst.logic.DynamicBinaryTree;
import net.joaosilva.bst.logic.Node;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicBstApp {

	public static void main(String[] args) {
		//System.out.println("Olá");

		DynamicBinaryTree<Integer> integerTree = new DynamicBinaryTree<>(
				(a, b) -> {return b-a;});

		for(int i = 0; i<20; i++){
			int in = (int) (Math.random() * 20);
			integerTree.addNode(new Node<>(in));
		}

		integerTree.print();
		System.out.println("==========================================");

		int randomNodeVal;
		Node<Integer> nodeVal = null;
		do{
			randomNodeVal = (int) (Math.random() * 41);
			nodeVal = integerTree.find(randomNodeVal);

		}while (nodeVal==null);
		System.out.println("Vou remover o "+randomNodeVal);
		System.out.println("==========================================");
		integerTree.remove(nodeVal.getValue());

		integerTree.print();

		//System.out.println("Hello");
		//SpringApplication.run(BstApplication.class, args);
	}

}
