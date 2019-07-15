package trello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.NumberFormat;
import java.util.Locale;

@Controller
public class MainController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/")
    public String calc(@RequestParam(required = false, defaultValue = "10") int years,
                       @RequestParam(required = false, defaultValue = "4000000") int loan,
                       @RequestParam(required = false, defaultValue = "10") float rate,
                       @RequestParam(required = false, defaultValue = "24000") int rent,
                       @RequestParam(required = false, defaultValue = "6") float percent,
                       Model model) {

        int pay = payCalculate(loan, years, rate);
        int total = pay * years * 12;
        int overpay = total - loan;

        int diff = pay - rent;
        float mPersent = percent/100/12;
        int msave = saveCalculate(diff, 12, mPersent);
        int ysave = saveCalculate(diff, years * 12, mPersent);

        Locale loc = new Locale("ru");
        NumberFormat formatter = NumberFormat.getInstance(loc);

        model.addAttribute("pay", formatter.format(pay));
        model.addAttribute("total", formatter.format(total));
        model.addAttribute("overpay", formatter.format(overpay));
        model.addAttribute("diff", formatter.format(diff));
        model.addAttribute("msave", formatter.format(msave));
        model.addAttribute("ysave", formatter.format(ysave));
        return "main";
    }

    private int payCalculate(long loan, int years, float rate) {
        int months = years * 12;
        double mRate = rate/100/12;
        double a = mRate * Math.pow(1 + mRate, months);
        double b = Math.pow(1 + mRate, months) - 1;
        return (int) (loan * a/b);
    }

    private int saveCalculate(int diff, int months, float percent) {
        float save = diff;
        for (int i = 0; i < months - 1; i++) {
            save = save + diff + save * percent;
        }
        return (int) save;
    }
}
