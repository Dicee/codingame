package codeeval.easy.WorkingExperience;

object Main extends App {
	def computeIntersections(p: Period, periods: List[Period]): List[Period] = {
	    def aux(periods: List[Period]): (List[Period],Option[Period]) = periods match {
	    	case h::q => 
	    		val tail = aux(q) 
				if (h intersects p) 
				    (tail._1,Some(if (tail._2.isDefined) tail._2.get intersection h else h intersection p))
				else 
					(h :: tail._1,tail._2)
			case Nil => (Nil,None)
	    }
	    val res = aux(periods)
	    res._2.getOrElse(p) :: res._1
	}
	
    scala.io.Source.fromFile(args(0)).getLines
    	.filter(!_.isEmpty)
        .map(line => {
            val dates   = line.split(";\\s").map(new Period(_))
            var periods = List(dates(0))
            for (i <- Range(1,dates.length)) periods = computeIntersections(dates(i),periods)
            periods.map(_.duration).sum/12
        })
        .foreach(println)
        println(46/12d+" " + 3.8.round)
}

class Date(s: String) extends Ordered[Date] {
	var split = s.split("\\s+")
	val month = split(0)
	val year  = split(1).toInt
	
    override def compare(that: Date) = (Date.months(month) + year*13) compareTo (Date.months(that.month) + that.year*13)
    override def toString = month + " " + year
}

object Date {
    val months = 
        Map("Jan" -> 1, "Feb" -> 2, "Mar" -> 3, "Apr" -> 4 , "May" -> 5 , "Jun" -> 6,
            "Jul" -> 7, "Aug" -> 8, "Sep" -> 9, "Oct" -> 10, "Nov" -> 11, "Dec" -> 12)
    def apply(year: Int, month: String) = new Date(month + " " + year)
    def apply(year: Int, month: Int)    = new Date(months.keys.find(months(_) == month).get + " " + year)
    def min(d0: Date, d1: Date)         = if (d0 <= d1) d0 else d1
    def max(d0: Date, d1: Date)         = if (d0 <= d1) d1 else d0
}

class Period(val start: Date, val end: Date) {
    def this(s: String) = this(new Date(s.substring(0,s.indexOf("-"))),new Date(s.substring(s.indexOf("-") + 1)))
    def intersects(that: Period) = Date.min(that.end,this.end) >= Date.max(that.start,this.start)
    def intersection(that: Period) = {
        def newBound(cmp: (Int,Int) => Int)(p: Period => Date)(f: Date => Int, g: Date => Int) = 
            Date(cmp(f(p(this)),f(p(that))),cmp(g(p(this)),g(p(that))))
        new Period(if (start <= that.start) start else that.start,if (end >= that.end) end else that.end)
    }
    def duration = (end.year - start.year)*12 + Date.months(end.month) - Date.months(start.month) + 1
    override def toString = start + "-" + end
}
