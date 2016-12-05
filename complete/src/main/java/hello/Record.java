package hello;

public final class Record {
   private final int    age;           
   private final String sex;        
   private final String civil;      
   private final int    children;      
   private final int    car;           
   private final String house;      
   private final String subdivision;
   private final String employment; 
   private final int    annum;         
   private final int    assets;        
   private final int    liabilities;   
   
   private final double appraisal;

   public Record(int age, String sex, String civil, int children, int car, String house, String subdivision, String employment, int annum, int assets, int liabilities, double appraisal) {
      this.age         = age;
      this.sex         = sex;
      this.civil       = civil;
      this.children    = children;
      this.car         = car;
      this.house       = house;
      this.subdivision = subdivision;
      this.employment  = employment;
      this.annum       = annum;
      this.assets      = assets;
      this.liabilities = liabilities;
      this.appraisal   = appraisal;
   }

   public int getAge() {
      return age;
   }

   public String getSex() {
      return sex;
   }

   public String getCivil() {
      return civil;
   }

   public int getChildren() {
      return children;
   }

   public int getCar() {
      return car;
   }

   public String getHouse() {
      return house;
   }

   public String getSubdivision() {
      return subdivision;
   }

   public String getEmployment() {
      return employment;
   }

   public int getAnnum() {
      return annum;
   }

   public int getAssets() {
      return assets;
   }

   public int getLiabilities() {
      return liabilities;
   }

   public double getAppraisal() {
      return appraisal;
   }
   
   
}

//   public Record(double appraisal) {
//      this.appraisal = appraisal;
//   }
//
//   public double getAppraisal() {
//      return appraisal;
//   }
