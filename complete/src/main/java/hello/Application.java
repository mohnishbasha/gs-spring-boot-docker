package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

@SpringBootApplication
@RestController
public class Application {
	
	private static final StatsDClient statsd = new NonBlockingStatsDClient(
            "springboot.home",                          /* prefix to any stats; may be null or empty string */
            "localhost",                        /* common case: localhost */
            8125,                                 /* port */
            new String[] {"tag:role:springboot"}            /* DataDog extension: Constant tags, always applied */
        );

	@RequestMapping("/")
	public String home() {
		statsd.incrementCounter("pageview");
                statsd.recordGaugeValue("pageviewguage", 100);
                statsd.recordGaugeValue("pageviewfloat", 0.01); /* DataDog extension: support for floating-point gauges */
                statsd.recordHistogramValue("pageviewhist", 15);     /* DataDog extension: histograms */
                statsd.recordHistogramValue("pageviewhist", 15.5);   /* ...also floating-point */

                /* Compatibility note: Unlike upstream statsd, DataDog expects execution times to be a
                * floating-point value in seconds, not a millisecond value. This library
                * does the conversion from ms to fractional seconds.
                */
                statsd.recordExecutionTime("pageviewclustertag", 25, "cluster:foo"); /* DataDog extension: cluster tag */
		return "Hello Docker World";
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
