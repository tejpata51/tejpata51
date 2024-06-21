import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.*;

public class InstantiateXml1 {
    private String paramFilePath;
    private ArrayList<Context> contextList;//Context|identifier|tailoring 
    private ArrayList<Solution> solutionList;//Solution|identifier|assessment
    private ArrayList<Goal> goalList;
    /*MainGoal|family|number|identifier|implemented by|control|baseline allocation|provision set|1
	ReqGoal|identifier|assessment process|functional requirement|1
	moduleGoal|identifier|assessment process|1
	opGoal|identifier|variable|condition|1*/
    private ArrayList<Strategy> strategyList; //strategy|identifier|assessment process
   
    private ArrayList<InContextOf> inContextOfLinks;//InContextOf|contextSourceGoal|contextDestinationContext
    
    private ArrayList<SupportedBy> supportedByLinks;
    //SupportedBy|supportSourceWithProvideRequires|supportDestinationWithProvideRequires|provision set

    public InstantiateXml1(String paramFilePath){
        this.paramFilePath = paramFilePath;
        contextList = new ArrayList();
        solutionList = new ArrayList<>();
        goalList = new ArrayList<>();
        strategyList = new ArrayList<>();
        inContextOfLinks = new ArrayList<>();
        supportedByLinks = new ArrayList<>();
    }

    public void CollectContextNodes ()
    {
        File file = new File(this.paramFilePath);
        try{
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] params = line.split("\\|");
                if((params.length == 3)&& (params[0].equalsIgnoreCase("context"))){
                	Context context = new Context(params[1], params[2]);
                	contextList.add(context);
                }
//                if(params.length == 3){
//                    if (params[0].equalsIgnoreCase("context")) {
//                       Context context = new Context(params[1], params[2]);
//                       contextList.add(context);
//
//                    }
 //               }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void CollectSolutionNodes ()
    {
        File file = new File(this.paramFilePath);
        try{
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] params = line.split("\\|");
                if (params[0].equalsIgnoreCase("solution")) {
                	Solution solution = new Solution(params[1], params[2]); //identifier|assessment
                	solutionList.add(solution);
                }
 //               if(params.length == 3){
//                    if (params[0].equalsIgnoreCase("solution")) {
//                        Solution solution = new Solution(params[1], params[2]);
//                        solutionList.add(solution);
//
//                    }
 //               }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

  //String identifier, String achievementWeight
    public void CollectGoalNodes ()
    {
        File file = new File(this.paramFilePath);
        try{
            Scanner sc = new Scanner(file);
          
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] params = line.split("\\|");
                if (params[0].equalsIgnoreCase("maingoal")){
                    if(params.length == 9){
                    	//MainGoal|family|number|identifier|implemented by|control|baseline allocation|provision set|1
                        MainGoal mainGoal = new MainGoal(params[1], params[2], params[3], params[4],
                                params[5], params[6], params[7],params[8]) ;

                        goalList.add(mainGoal);
                    }
                }

               else if (params[0].equalsIgnoreCase("reqgoal")){
                    if(params.length == 5){
                    	//ReqGoal|identifier|assessment process|functional requirement|1
                        ReqGoal reqGoal = new ReqGoal(params[1], params[2], params[3], params[4]) ;

                        goalList.add(reqGoal);
                    }
                }
                else if (params[0].equalsIgnoreCase("modulegoal")){
                    if(params.length == 4){
                    	//moduleGoal|identifier|assessment process|1
                    	
                        ModuleGoal modGoal = new ModuleGoal(params[1],params[2], params[3]) ;

                        goalList.add(modGoal);
                    }
                }
                else if (params[0].equalsIgnoreCase("opgoal")){
                    if(params.length == 5){
                    	//opGoal|identifier|variable|condition|1
                        OppGoal oppGoal = new OppGoal(params[1],params[2],params[3], params[4]) ;

                        goalList.add(oppGoal);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void CollectStrategyNodes ()
    {
        File file = new File(this.paramFilePath);
        try{
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] params = line.split("\\|");
                if(params.length == 3){
                    if (params[0].equalsIgnoreCase("strategy")) {
                        Strategy strategy = new Strategy(params[1], params[2]);
                        strategyList.add(strategy);

                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void CollectInContextOfLinks ()
    {
        File file = new File(this.paramFilePath);
        try{
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] params = line.split("\\|");
                if(params.length == 3){
                    if (params[0].equalsIgnoreCase("incontextof")) {
                        InContextOf inContextOf = new InContextOf(params[1], params[2]);
                        inContextOfLinks.add(inContextOf);

                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void CollectSupportedByLinks ()
    {
        File file = new File(this.paramFilePath);
        try{
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] params = line.split("\\|");
                if(params[0].equalsIgnoreCase("supportedby")){
                    if (params.length==3) {
                        SupportedBy supportedBy = new SupportedBy(params[1], params[2]);
                        supportedByLinks.add(supportedBy);

                    }
                    else if (params.length==4) {
                        ProvidesRequires supportedBy = new ProvidesRequires(params[1], params[2], params[3]);
                        supportedByLinks.add(supportedBy);

                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public ArrayList<Context> getContextList() {
        return contextList;
    }

    public ArrayList<Solution> getSolutionList() {
        return solutionList;
    }

    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    public ArrayList<Strategy> getStrategyList() {
        return strategyList;
    }

    public ArrayList<InContextOf> getInContextOfLinks() {
        return inContextOfLinks;
    }

    public ArrayList<SupportedBy> getSupportedByLinks() {
        return supportedByLinks;
    }
}
