// Name: Xinyi Cai
// USC NetID: xcai6647
// CS 455 PA1
// Spring 2023

/**
 * CoinTossSimulatorTester class
 * A non-interactive, independent tester of the CoinTossSimulator class.
 *
 */
public class CoinTossSimulatorTester {
    /**
     * Test the result of run method in CoinTossSimulator
     * with coded invariants for count.
     * The whole procedure include constructing, running for multiple
     * time, resetting, and running again.
     * Prints out the results for getNumTrials(), getTwoHeads(),
     * getTwoTails() and getHeadTails(). Compares their relationship
     * to the expected one.
     *
     */
    public static void main(String[] args){
        CoinTossSimulator tester = new CoinTossSimulator();
        System.out.println("After constructor:");

        System.out.print("Number of trails [exp:0]: ");
        System.out.println(tester.getNumTrials());
        System.out.print("Two-head tosses: ");
        System.out.println(tester.getTwoHeads());
        System.out.print("Two-tail tosses: ");
        System.out.println(tester.getTwoTails());
        System.out.print("One-head one-tail tosses: ");
        System.out.println(tester.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println((tester.getNumTrials()==tester.getTwoHeads()+tester.getTwoTails()+tester.getHeadTails())?
                "true":"false");
        System.out.println();

        tester.run(1);
        System.out.print("Number of trails [exp:1]: ");
        System.out.println(tester.getNumTrials());
        System.out.print("Two-head tosses: ");
        System.out.println(tester.getTwoHeads());
        System.out.print("Two-tail tosses: ");
        System.out.println(tester.getTwoTails());
        System.out.print("One-head one-tail tosses: ");
        System.out.println(tester.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println((tester.getNumTrials()==tester.getTwoHeads()+tester.getTwoTails()+tester.getHeadTails())?
                "true":"false");
        System.out.println();

        tester.run(10);
        System.out.print("Number of trails [exp:1]: ");
        System.out.println(tester.getNumTrials());
        System.out.print("Two-head tosses: ");
        System.out.println(tester.getTwoHeads());
        System.out.print("Two-tail tosses: ");
        System.out.println(tester.getTwoTails());
        System.out.print("One-head one-tail tosses: ");
        System.out.println(tester.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println((tester.getNumTrials()==tester.getTwoHeads()+tester.getTwoTails()+tester.getHeadTails())?
                "true":"false");
        System.out.println();

        tester.run(100);
        System.out.print("Number of trails [exp:111]: ");
        System.out.println(tester.getNumTrials());
        System.out.print("Two-head tosses: ");
        System.out.println(tester.getTwoHeads());
        System.out.print("Two-tail tosses: ");
        System.out.println(tester.getTwoTails());
        System.out.print("One-head one-tail tosses: ");
        System.out.println(tester.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println((tester.getNumTrials()==tester.getTwoHeads()+tester.getTwoTails()+tester.getHeadTails())?
                "true":"false");
        System.out.println();

        tester.run(10000);
        System.out.print("Number of trails [exp:10111]: ");
        System.out.println(tester.getNumTrials());
        System.out.print("Two-head tosses: ");
        System.out.println(tester.getTwoHeads());
        System.out.print("Two-tail tosses: ");
        System.out.println(tester.getTwoTails());
        System.out.print("One-head one-tail tosses: ");
        System.out.println(tester.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println((tester.getNumTrials()==tester.getTwoHeads()+tester.getTwoTails()+tester.getHeadTails())?
                "true":"false");
        System.out.println();

        tester.reset();
        System.out.println("After Reset: ");

        //tester.run(0);
        System.out.print("Number of trails [exp:0]: ");
        System.out.println(tester.getNumTrials());
        System.out.print("Two-head tosses: ");
        System.out.println(tester.getTwoHeads());
        System.out.print("Two-tail tosses: ");
        System.out.println(tester.getTwoTails());
        System.out.print("One-head one-tail tosses: ");
        System.out.println(tester.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println((tester.getNumTrials()==tester.getTwoHeads()+tester.getTwoTails()+tester.getHeadTails())?
                "true":"false");
        System.out.println();

        tester.run(1000);
        System.out.print("Number of trails [exp:1000]: ");
        System.out.println(tester.getNumTrials());
        System.out.print("Two-head tosses: ");
        System.out.println(tester.getTwoHeads());
        System.out.print("Two-tail tosses: ");
        System.out.println(tester.getTwoTails());
        System.out.print("One-head one-tail tosses: ");
        System.out.println(tester.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println((tester.getNumTrials()==tester.getTwoHeads()+tester.getTwoTails()+tester.getHeadTails())?
                "true":"false");
        System.out.println();

        tester.run(999999);
        System.out.print("Number of trails [exp:1000999]: ");
        System.out.println(tester.getNumTrials());
        System.out.print("Two-head tosses: ");
        System.out.println(tester.getTwoHeads());
        System.out.print("Two-tail tosses: ");
        System.out.println(tester.getTwoTails());
        System.out.print("One-head one-tail tosses: ");
        System.out.println(tester.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println((tester.getNumTrials()==tester.getTwoHeads()+tester.getTwoTails()+tester.getHeadTails())?
                "true":"false");
        System.out.println();

    }
}
