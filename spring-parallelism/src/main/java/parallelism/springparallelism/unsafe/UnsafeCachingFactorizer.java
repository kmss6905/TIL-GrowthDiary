package parallelism.springparallelism.unsafe;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(name="UnsafeCachingFactorizerServlet", urlPatterns={"/unsafe/factorizer"})
public class UnsafeCachingFactorizer extends HttpServlet {
  private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
  private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

  }
}
