import java.util.ArrayList;

/**
  The main class for testing hastable implementation
*/
public class MA3Main
{
    public static void main(String args[])
    {
        LinearHashTable<String, String> lht = new LinearHashTable<>(new SimpleStringHasher());
		BucketHashTable<String, String> bht = new BucketHashTable<>(new SimpleStringHasher());

		MA3Main.linearHashTableTest(lht);
		MA3Main.bucketHashTableTest(bht);

	}

    public static void linearHashTableTest(LinearHashTable<String, String> ht)
    {
    	// TODO: Write your own test cases. Print out any statement you'd like/need to.

    }

    public static void bucketHashTableTest(BucketHashTable<String, String> ht)
    {
    	// TODO: Write your own test cases. Print out any statement you'd like/need to.

    }
}
