package trello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //Все http запросы обрабатываются контроллером
public class MainController {

    // Аннотация @RequestMapping гарантирует, что HTTP запросы к /greeting приведут к выполнению метода greeting()
    // @RequestParam связывает значение параметра name запроса, с name параметром метода greeting().
    // Этот параметр не required; если он отсутствует в запросе, то будет использовано defaultValue значение "World".
    // Значение параметра name добавлено в объект Model, что делает его доступным в шаблоне представления.

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
    public String calc(@RequestParam(required = false, defaultValue = "10") int time,
                       @RequestParam(required = false, defaultValue = "5000000") int price,
                       @RequestParam(required = false, defaultValue = "1000000") int first,
                       @RequestParam(required = false, defaultValue = "10") int rate,
                       @RequestParam(required = false, defaultValue = "20000") int rent,
                       Model model) {

        Long pay = null;
        Long total = null;
        Long overpay = null;


        model.addAttribute("pay", pay);
        model.addAttribute("total", total);
        model.addAttribute("overpay", overpay);
        return "main";
    }

}
