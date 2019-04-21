package itemexchange;

import itemexchange.ui.ItemexchangeUi;
import itemexchange.ui.TextInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    TextInterface iface;
    
    @Autowired
    ItemexchangeUi itemexchangeUi;
    

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        try {
            iface.start();
            itemexchangeUi.init();
        } catch (Exception ex) {
            Logger.getLogger(ApplicationStartup.class.getName()).log(Level.SEVERE, null, ex);
        }

        return;
    }

}
