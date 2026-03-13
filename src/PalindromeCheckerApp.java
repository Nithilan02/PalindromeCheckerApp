import java.util.*;

public class PalindromeCheckerApp {

    // ---------- UC8 Linked List Node ----------
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    // ---------- UC11 OOP Service ----------
    static class PalindromeChecker {

        public boolean checkPalindrome(String str) {

            str = str.toLowerCase().replaceAll("[^a-z0-9]", "");

            Stack<Character> stack = new Stack<>();

            for (char c : str.toCharArray()) {
                stack.push(c);
            }

            String reversed = "";

            while (!stack.isEmpty()) {
                reversed += stack.pop();
            }

            return str.equals(reversed);
        }
    }

    // ---------- UC12 Strategy Pattern ----------
    interface PalindromeStrategy {
        boolean check(String str);
    }

    static class StackStrategy implements PalindromeStrategy {

        public boolean check(String str) {

            str = str.toLowerCase().replaceAll("[^a-z0-9]", "");

            Stack<Character> stack = new Stack<>();

            for (char c : str.toCharArray()) {
                stack.push(c);
            }

            String reversed = "";

            while (!stack.isEmpty()) {
                reversed += stack.pop();
            }

            return str.equals(reversed);
        }
    }

    static class DequeStrategy implements PalindromeStrategy {

        public boolean check(String str) {

            str = str.toLowerCase().replaceAll("[^a-z0-9]", "");

            Deque<Character> deque = new ArrayDeque<>();

            for (char c : str.toCharArray()) {
                deque.addLast(c);
            }

            while (deque.size() > 1) {
                if (deque.removeFirst() != deque.removeLast())
                    return false;
            }

            return true;
        }
    }

    // ---------- UC2 / UC4 Reverse ----------
    public static String reverseString(String str) {

        String reversed = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }

        return reversed;
    }

    // ---------- UC3 Basic ----------
    public static boolean isPalindromeBasic(String str) {
        return str.equals(reverseString(str));
    }

    // ---------- UC5 Ignore Case ----------
    public static boolean isPalindromeIgnoreCase(String str) {
        return isPalindromeBasic(str.toLowerCase());
    }

    // ---------- UC6 Ignore Spaces ----------
    public static boolean isPalindromeIgnoreSpaces(String str) {
        return isPalindromeBasic(str.replace(" ", "").toLowerCase());
    }

    // ---------- UC7 Deque ----------
    public static boolean isPalindromeDeque(String str) {

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            deque.addLast(c);
        }

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast())
                return false;
        }

        return true;
    }

    // ---------- UC8 Linked List ----------
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

    // ---------- UC9 Recursive ----------
    public static boolean isPalindromeRecursive(String str, int start, int end) {

        if (start >= end)
            return true;

        if (str.charAt(start) != str.charAt(end))
            return false;

        return isPalindromeRecursive(str, start + 1, end - 1);
    }

    // ---------- UC10 Normalized ----------
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

    // ---------- UC13 Performance Comparison ----------
    public static void comparePerformance(String input) {

        long start, end;

        System.out.println("\n--- Performance Comparison ---");

        start = System.nanoTime();
        isPalindromeBasic(input);
        end = System.nanoTime();
        System.out.println("Basic Method Time: " + (end - start) + " ns");

        start = System.nanoTime();
        isPalindromeDeque(input);
        end = System.nanoTime();
        System.out.println("Deque Method Time: " + (end - start) + " ns");

        start = System.nanoTime();
        isPalindromeLinkedList(input);
        end = System.nanoTime();
        System.out.println("Linked List Method Time: " + (end - start) + " ns");

        start = System.nanoTime();
        isPalindromeRecursive(input, 0, input.length() - 1);
        end = System.nanoTime();
        System.out.println("Recursive Method Time: " + (end - start) + " ns");

        start = System.nanoTime();
        isPalindromeNormalized(input);
        end = System.nanoTime();
        System.out.println("Normalized Method Time: " + (end - start) + " ns");
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // UC1
        System.out.println("Welcome to Palindrome Checker App");

        System.out.print("Enter a word or sentence: ");
        String input = sc.nextLine();

        System.out.println("Basic Palindrome: " + isPalindromeBasic(input));
        System.out.println("Ignore Case: " + isPalindromeIgnoreCase(input));
        System.out.println("Ignore Spaces: " + isPalindromeIgnoreSpaces(input));
        System.out.println("Deque Palindrome: " + isPalindromeDeque(input));
        System.out.println("Linked List Palindrome: " + isPalindromeLinkedList(input));
        System.out.println("Recursive Palindrome: " +
                isPalindromeRecursive(input, 0, input.length() - 1));
        System.out.println("Normalized Palindrome: " + isPalindromeNormalized(input));

        // UC11 OOP Service
        PalindromeChecker checker = new PalindromeChecker();
        System.out.println("OOP Service Palindrome: " + checker.checkPalindrome(input));

        // UC12 Strategy Pattern
        PalindromeStrategy strategy;

        strategy = new StackStrategy();
        System.out.println("Strategy Stack Result: " + strategy.check(input));

        strategy = new DequeStrategy();
        System.out.println("Strategy Deque Result: " + strategy.check(input));

        // UC13 Performance Comparison
        comparePerformance(input);

        sc.close();
    }
}