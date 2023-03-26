import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
public class BinarySearchTree<E> {
    private Node<E> root;
    private Comparator<? super E> comparator;

    //Constructor que acepta un Comparator
    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public E find(E stringStringAssociation) {
        return null;
    }

    //Clase interna que representa cada nodo del árbol
    private static class Node<E> {
        private E data;
        private Node<E> left;
        private Node<E> right;

        public Node(E data) {
            this.data = data;
        }
    }

    //Método que añade un elemento al árbol
    public void add(E data) {
        root = addRecursive(root, data);
    }

    //Método recursivo que añade un elemento al árbol
    private Node<E> addRecursive(Node<E> current, E data) {
        if (current == null) {
            return new Node<>(data);
        }

        int compareResult = comparator.compare(data, current.data);
        if (compareResult < 0) {
            current.left = addRecursive(current.left, data);
        } else if (compareResult > 0) {
            current.right = addRecursive(current.right, data);
        }

        return current;
    }

    //Método que busca un elemento en el árbol
    public boolean contains(E data) {
        return containsRecursive(root, data);
    }

    //Método recursivo que busca un elemento en el árbol
    private boolean containsRecursive(Node<E> current, E data) {
        if (current == null) {
            return false;
        }

        int compareResult = comparator.compare(data, current.data);
        if (compareResult < 0) {
            return containsRecursive(current.left, data);
        } else if (compareResult > 0) {
            return containsRecursive(current.right, data);
        } else {
            return true;
        }
    }

    //Método que elimina un elemento del árbol
    public boolean remove(E data) {
        Node<E> parent = null;
        Node<E> current = root;

        while (current != null) {
            int compareResult = comparator.compare(data, current.data);
            if (compareResult == 0) {
                if (current.left == null && current.right == null) {
                    // Caso 1: El nodo a eliminar no tiene hijos
                    if (parent == null) {
                        root = null;
                    } else if (current == parent.left) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else if (current.left != null && current.right == null) {
                    // Caso 2: El nodo a eliminar tiene un hijo
                } else if (current.left != null && current.right == null) {
                    // Caso 2: El nodo a eliminar tiene un hijo
                    if (parent == null) {
                        root = current.left;
                    } else if (current == parent.left) {
                        parent.left = current.left;
                    } else {
                        parent.right = current.left;
                    }
                } else if (current.left == null && current.right != null) {
                    // Caso 2: El nodo a eliminar tiene un hijo
                    if (parent == null) {
                        root = current.right;
                    } else if (current == parent.left) {
                        parent.left = current.right;
                    } else {
                        parent.right = current.right;
                    }
                } else {
                    // Caso 3: El nodo a eliminar tiene dos hijos
                    Node<E> successor = findSuccessor(current);
                    current.data = successor.data;
                    remove(successor.data);
                }

                return true;
            } else if (compareResult < 0) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }

        return false;
    }

    //Método que encuentra el sucesor de un nodo
    private Node<E> findSuccessor(Node<E> node) {
        Node<E> current = node.right;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    //Método que devuelve los elementos del árbol en orden
    public List<E> inOrderTraversal() {
        List<E> result = new ArrayList<>();
        inOrderTraversalRecursive(root, result);
        return result;
    }

    //Método recursivo que realiza un recorrido en orden del árbol
    private void inOrderTraversalRecursive(Node<E> current, List<E> result) {
        if (current != null) {
            inOrderTraversalRecursive(current.left, result);
            result.add(current.data);
            inOrderTraversalRecursive(current.right, result);
        }
    }
}





