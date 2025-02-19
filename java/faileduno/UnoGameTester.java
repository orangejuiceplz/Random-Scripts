public Digits(int num)
{
    digitList = new ArrayList<Integer>();
    digitList.add(0, new Integer(num % 10));

    int numRemaining = num / 10;
    while(numRemaining > 0)
    {
        digitList.add(0, new Integer(numRemaining % 10));
        numRemaining /= 10;
    }
}