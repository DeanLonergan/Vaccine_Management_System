package com.vaccine_management_system;

import java.util.*;

public class CustomList<L> implements List<L> {
    public CustomNode<L> head = null;

    /**
     * Method to add an element to the start of a linked list.
     * @param data The data stored within the node.
     */
    public void addFirst(L data) {
        CustomNode<L> node = new CustomNode<>(data);
        node.next=head;
        head=node;
    }

    /**
     * Method to add an element to the end of a linked list.
     * @param data The data stored within the node.
     */
    public void addLast(L data) {
        CustomNode<L> node = new CustomNode<>(data);
        if(head==null) head=node;
        else {
            CustomNode<L> last=head;
            while(last.next!=null) last=last.next;
            last.next=node;
        }
    }

    @Override
    public int size() {
        CustomNode<L> currentNode=head;
        int size=0;
        while (currentNode!=null) {
            size++;
            currentNode=currentNode.next;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(Object o) {
        CustomNode<L> current=head;
        while(current!=null) {
            if (current.getData().equals(o)) return true;
            current=current.next;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (head==null) return false;
        else if (head.getData().equals(o)) {
            head=head.next;
            return true;
        }
        else {
            CustomNode<L> previous=head;
            CustomNode<L> current=head.next;
            while (current!=null) {
                if (current.getData().equals(o)) {
                    previous.next=current.next;
                    return true;
                }
                previous=current;
                current=current.next;
            }
        }
        return false;
    }

    @Override
    public L remove(int index) {
        CustomNode<L> temp=head;
        if(head==null) return null;
        else if(size()==1) {
            head=null;
            return temp.getData();
        }
        else if(index==0) {
            head=head.next;
            return temp.getData();
        }
        else {
            CustomNode<L> previous=head;
            CustomNode<L> current=head.next;
            int counter=0;
            while (current!=null && counter++<index) {
                if(counter==index) {
                    previous.next=current.next;
                    return current.getData();
                }
                previous=current;
                current=current.next;
            }
            return null;
        }
    }

    @Override
    public void clear() {
        head=null;
    }

    @Override
    public L get(int index) {
        if(head==null) return null;
        else if(index==0) return head.getData();
        else {
            CustomNode<L> current=head.next;
            int counter=0;
            while (current!=null && counter++<index) {
                if(counter==index) return current.getData();
                current=current.next;
            }
            return null;
        }
    }

    @Override
    public L set(int index, L element) {
        if(head==null) return null;
        else if(index==0) {
            head.setData(element);
            return head.getData();
        } else {
            CustomNode<L> current=head.next;
            int counter=0;
            while (current != null && counter++ < index) {
                if (counter == index) {
                    current.setData(element);
                    return current.getData();
                }
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public void add(int index, L data) {
        CustomNode<L> node = new CustomNode<>(data);
        if(index==0) {
            addFirst(node.getData());
            return;
        }
        CustomNode<L> previous=head;
        CustomNode<L> current=head.next;
        int counter=0;
        while(current!=null && counter++<index) {
            if(counter==index) {
                previous.next=node;
                node.next=current;
                break;
            }
            previous=current;
            current=current.next;
        }
    }

    @Override
    public boolean add(L e) {
        addFirst(e);
        return true;
    }

    @Override
    public int indexOf(Object o) {
        if (head==null) return -1;
        else if (head.getData().equals(o)) return 0;
        else {
            CustomNode<L> current=head.next;
            int counter=0;
            while (current!=null && counter++<=size()) {
                if (current.getData().equals(o)) return counter;
                current=current.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return -1;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public Iterator<L> iterator() {
        return null;
    }

    @Override
    public ListIterator<L> listIterator() {
        return null;
    }

    @Override
    public ListIterator<L> listIterator(int index) {
        return null;
    }

    @Override
    public List<L> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

}
