package io.jenkins.plugins.demoauth;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.User;
import hudson.PluginManager;
import hudson.security.AuthorizationStrategy;
import hudson.util.FormValidation;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import jenkins.model.Jenkins;
import net.sf.json.JSONObject;
import org.acegisecurity.acls.sid.PrincipalSid;
import org.acegisecurity.acls.sid.Sid;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.DoNotUse;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import hudson.security.ACL;
import hudson.security.Permission;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

public class AuthorizationStrategyImpl extends AuthorizationStrategy {
    private final Logger log = Logger.getLogger(AuthorizationStrategyImpl.class.getName());
    private final Set<String> sids;
    private final transient AclImpl acl;

    private String filename = "demoauth.yml";

    public AuthorizationStrategyImpl() {
			this("demoauth.yml");
    }
    @DataBoundConstructor
    public AuthorizationStrategyImpl(String filename) {
      this.filename = filename;
      this.acl = new AclImpl();
      this.sids = new HashSet<>();
    }
    public String getFilename() {
      return filename;
    }

    public void setFilename(String filename) {
      this.filename = filename;
    }

    @Override
    @Nonnull
    public ACL getRootACL() {
        log.info("getRootAC");
      //  log.info(filename);
        //log.info(Boolean.toString(acl == null));
        //log.info(Boolean.toString(sids == null));
        return acl;
    }

    @Override
    @Nonnull
    public Set<String> getGroups() {
        log.info("getGroups: ");
        final TreeSet<String> sids = new TreeSet<>();
        sids.addAll(this.sids);
        return sids;
    }

    @Restricted(NoExternalUse.class)
    public static Descriptor<AuthorizationStrategy> DESCRIPTOR;

    @Extension @Symbol("demoauth")
    public static final class DescriptorImpl extends Descriptor<AuthorizationStrategy> {
        public DescriptorImpl() {
            DESCRIPTOR = this;
        }
        public String getDisplayName() {
            return "demoauth";
        }
    }
}
