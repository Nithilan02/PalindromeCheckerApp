import java.util.*;

public class PalindromeCheckerApp {

    // ---------- UC8: Linked List Node ----------
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    // ---------- UC2 / UC4: Reverse String ----------
    public static String reverseString(String str) {
        String reversed = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }

        return reversed;
    }

    // ---------- UC3: Basic Palindrome ----------
    public static boolean isPalindromeBasic(String str) {
        String reversed = reverseString(str);
        return str.equals(reversed);
    }

    // ---------- UC5: Case Insensitive ----------
    public static boolean isPalindromeIgnoreCase(String str) {
        str = str.toLowerCase();
        return isPalindromeBasic(str);
    }

    // ---------- UC6: Ignore Spaces ----------
    public static boolean isPalindromeIgnoreSpaces(String str) {
        str = str.replace(" ", "").toLowerCase();
        return isPalindromeBasic(str);
    }

    // ---------- UC7: Deque Based ----------
    public static boolean isPalindromeDeque(String str) {

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            deque.addLast(c);
        }

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }

        return true;
    }

    // ---------- UC8: Linked List Based ----------
    public static boolean isPalindromeLinkedList(String str) {

        Node head = new Node(str.charAt(0));
        Node current = head;

        for (int i = 1; i < str.length(); i++) {
            current.next = new Node(str.charAt(i));
            current = current.next;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node prev = null;
        Node next;

        while (slow != null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        Node first = head;
        Node second = prev;

        while (second != null) {
            if (first.data != second.data)
                return false;

            first = first.next;
            second = second.next;
        }

        return true;
    }

    // ---------- UC9: Recursive ----------
    public static boolean isPalindromeRecursive(String str, int start, int end) {

        if (start >= end)
            return true;

        if (str.charAt(start) != str.charAt(end))
            return false;

        return isPalindromeRecursive(str, start + 1, end - 1);
    }

    // ---------- UC10: Ignore Case & Spaces ----------
    public static boolean isPalindromeNormalized(String str) {

        str = str.toLowerCase().replaceAll("[^a-z0-9]", "");

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {

            if (str.charAt(left) != str.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }

    // ---------- MAIN METHOD ----------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // UC1
        System.out.println("Welcome to Palindrome Checker App");

        System.out.print("Enter a word or sentence: ");
        String input = sc.nextLine();

        // UC3
        System.out.println("Basic Palindrome: " + isPalindromeBasic(input));

        // UC5
        System.out.println("Case Insensitive Palindrome: " + isPalindromeIgnoreCase(input));

        // UC6
        System.out.println("Ignore Spaces Palindrome: " + isPalindromeIgnoreSpaces(input));

        // UC7
        System.out.println("Deque Palindrome: " + isPalindromeDeque(input));

        // UC8
        System.out.println("Linked List Palindrome: " + isPalindromeLinkedList(input));

        // UC9
        System.out.println("Recursive Palindrome: " +
                isPalindromeRecursive(input, 0, input.length() - 1));

        // UC10
        System.out.println("Normalized Palindrome: " + isPalindromeNormalized(input));

        sc.close();
    }
}