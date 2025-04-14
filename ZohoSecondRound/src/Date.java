public class Date {
    static int[]months={31,28,31,30,31,30,31,31,30,31,30,31};
    private int day;
    private int month;
    private int year;
    public Date(int day,int month,int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }
    public Date addaDays(int days){
        this.day+=days;
        while(this.day>=noOfDaysInYear(this.year)){
            this.day-=noOfDaysInYear(this.year);
            this.year++;
        }
        while(this.day>months[this.month-1]){
            this.day-=months[this.month-1];
            this.month++;
            if(this.month>12){
                this.year++;
                this.month=1;
            }
        }
        return this;
    }
    public int noOfDaysInYear(int year){
        if(year%4==0){
            return 366;
        }
        else{
            return 365;
        }
    }

    @Override
    public String toString() {
        String date=this.day>9?String.valueOf(this.day):("0"+String.valueOf(this.day));
        String month=this.month>9?String.valueOf(this.month):("0"+String.valueOf(this.month));

        return
                 date +"-"
                 + month +
                "-"+this.year ;
    }

}
