public class Main {
    public static void main(String[] args) {
        ContactList contacts = new ContactList();
        // Add contacts
        contacts.add(new Contact("John", "Doe",  "347-238-7923", "2900 bed ave", "Brooklyn", "NY"));
        contacts.add(new Contact("Jane",  "Smith", "111-234-5326", "300 ocean ave", "New Jersey", "NJ"));
        contacts.add(new Contact("Ari", "Mel", "222-111-64321", "500 ocean ave", "Brooklyn", "NC"));
        contacts.add(new Contact("John", "Melah", "122-323-4232", "643 ocean ave", "Pennsylvania", "NC"));


        // Print all contacts
        System.out.println("All Contacts:");
        //contacts.printContacts();
        for (Contact c : contacts) {
          System.out.println(c);
        }
      


        // Find a contact by name
        System.out.println("\nFind Contact:");
        Contact foundContact = contacts.findByLastName("Doe");
        System.out.println(foundContact);


        // Find a contact by email
        System.out.println("\nFind Contact:");
        foundContact = contacts.findByPhoneNumber("111-234-5326");
        System.out.println(foundContact);


        // Remove a contact
        System.out.println("\nRemove Contact:");


        Contact bobby = new Contact("Bob", "Johnson", "347-111-5555");
        contacts.add(bobby);
        System.out.println("All Contacts:");
        for (Contact c : contacts) {
          System.out.println(c);
        }


        contacts.remove(bobby);
        System.out.println("After removal:");


        for (Contact c : contacts) {
          System.out.println(c);


          // Find by City
          System.out.println("\nFind by City");
          String city = "Brooklyn";
          ContactList foundByCity = contacts.findAllByCity(city);


          if(!foundByCity.isEmpty()){
            System.out.println("\nContacts: ");
            for(Contact contact: foundByCity){ //type, var, value
              System.out.println(contact);
            }
          } else {
             System.out.println("No contacts found in " + city);
          }
          
        }


    }
}
