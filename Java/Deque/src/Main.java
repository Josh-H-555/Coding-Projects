public class Main
{

    public static void main(String[] args)
    {
        CircularArray myArray = new CircularArray();

        System.out.println(myArray.toString());

        myArray.insert(0, "poop");
        System.out.println(myArray.toString());
        myArray.insert(1, "poop");
        System.out.println(myArray.toString());
        myArray.insert(2, "poop");
        System.out.println(myArray.toString());
        myArray.insert(3, "poop");
        System.out.println(myArray.toString());
        System.out.println(myArray.getLength());

        myArray.delete(0);

    }
}