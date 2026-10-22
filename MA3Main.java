/**
 * The main class for testing hash table implementation.
 * Tests insertion, searching, updating, collision handling, and deletion.
 */
public class MA3Main
{
    public static void main(String args[])
    {
        BucketHashTable<String, String> bht =
            new BucketHashTable<>(new SimpleStringHasher());

        MA3Main.bucketHashTableTest(bht);
    }

    /**
     * Tests the BucketHashTable implementation.
     */
    public static void bucketHashTableTest(BucketHashTable<String, String> ht)
    {
        // Test 1: Insert two keys that produce the same hash.
        // Expected: Both keys should be found, and their values should be AB and BA.
        // "ab" and "ba" have the same character sum, so they collide.
        ht.addElement("ab", "AB");
        ht.addElement("ba", "BA");

        System.out.println("ab found (expected true): "
            + ht.containsElement("ab"));
        System.out.println("ba found (expected true): "
            + ht.containsElement("ba"));
        System.out.println("Value of ab (expected AB): "
            + ht.getElement("ab"));
        System.out.println("Value of ba (expected BA): "
            + ht.getElement("ba"));

        // Test 2: Insert more elements to trigger resizing.
        // Expected: All six distinct keys should remain accessible after resizing.
        ht.addElement("cat", "CAT");
        ht.addElement("dog", "DOG");
        ht.addElement("monkey", "MONKEY");
        ht.addElement("horse", "HORSE");

        System.out.println("Value of cat (expected CAT): "
            + ht.getElement("cat"));
        System.out.println("Value of horse (expected HORSE): "
            + ht.getElement("horse"));
        System.out.println("Value of ab after resizing (expected AB): "
            + ht.getElement("ab"));
        System.out.println("Value of ba after resizing (expected BA): "
            + ht.getElement("ba"));

        // Test 3: Update an existing key.
        // Expected: ab becomes ABC, while ba remains BA.
        ht.addElement("ab", "ABC");

        System.out.println("Updated ab (expected ABC): "
            + ht.getElement("ab"));
        System.out.println("Unchanged ba (expected BA): "
            + ht.getElement("ba"));

        // Test 4: Remove one key from the colliding pair.
        // Expected: ab is absent and returns null.
        // ba must remain present with its original value.
        ht.removeElement("ab");

        System.out.println("ab after removal (expected false): "
            + ht.containsElement("ab"));
        System.out.println("Value of removed ab (expected null): "
            + ht.getElement("ab"));
        System.out.println("ba after removing ab (expected true): "
            + ht.containsElement("ba"));
        System.out.println("Value of ba (expected BA): "
            + ht.getElement("ba"));

        // Test 5: Reinsert the deleted key.
        // Expected: ab becomes present again with value AB.
        // ba remains present with value BA.
        ht.addElement("ab", "AB");

        System.out.println("ab after reinsertion (expected true): "
            + ht.containsElement("ab"));
        System.out.println("Reinserted ab (expected AB): "
            + ht.getElement("ab"));
        System.out.println("ba still present (expected true): "
            + ht.containsElement("ba"));

        // Test 6: Search for a key that was never inserted.
        // Expected: false and null.
        System.out.println("missing key found (expected false): "
            + ht.containsElement("missing"));
        System.out.println("Missing key value (expected null): "
            + ht.getElement("missing"));

        System.out.println("\n----- All BucketHashTable Tests Finished! -----");
    }
}