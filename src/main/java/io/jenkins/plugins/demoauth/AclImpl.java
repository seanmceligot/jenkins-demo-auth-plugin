package io.jenkins.plugins.demoauth;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.acls.sid.PrincipalSid;
import org.acegisecurity.acls.sid.GrantedAuthoritySid;
import org.acegisecurity.acls.sid.Sid;
import hudson.security.Permission;
import hudson.security.ACL;
import javax.annotation.Nonnull;
import java.util.logging.Logger;
import static java.util.logging.Level.FINE;
import static java.util.logging.Level.FINER;

public class AclImpl extends ACL {
    private static final Logger log = Logger.getLogger(AclImpl.class.getName());

    @Override
    public boolean hasPermission(@Nonnull Authentication a, Permission perm) {
      log.info("hasPermission " + a.toString() + " " + perm.toString());
      return true;
    }
}
