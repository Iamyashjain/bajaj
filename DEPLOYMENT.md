# Deployment Guide for Vercel

## Prerequisites
- Java 17 or higher installed
- Maven installed
- Git installed
- GitHub account
- Vercel account (free tier is sufficient)

## Step-by-Step Deployment

### Phase 1: Configuration (5 minutes)

1. **Update Your Email**
   ```
   File: src/main/resources/application.properties
   Change: official.email=your.email@chitkara.edu.in
   ```

2. **Get Google Gemini API Key (Optional)**
   - Visit https://aistudio.google.com
   - Sign in with Google
   - Navigate to "Get API Key"
   - Create a new project or select existing
   - Generate API Key
   - Save it for later (you'll add it to Vercel)

### Phase 2: Test Locally (10 minutes)

1. **Build the project**
   ```bash
   cd c:\Users\DELL\Desktop\bajaj
   mvn clean install
   ```

2. **Run locally**
   ```bash
   mvn spring-boot:run
   ```

3. **Test endpoints**
   Open a new terminal:
   ```bash
   # Health check
   curl http://localhost:8080/health

   # Fibonacci test
   curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"fibonacci\": 5}"

   # Prime test
   curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"prime\": [2,3,4,5,6,7]}"
   ```

### Phase 3: Push to GitHub (5 minutes)

1. **Initialize Git** (if not already done)
   ```bash
   git init
   git add .
   git commit -m "Initial commit: BFHL API for Chitkara Qualifier"
   ```

2. **Create GitHub Repository**
   - Go to https://github.com/new
   - Repository name: `chitkara-bfhl-api` (or any name)
   - Make it **PUBLIC** (required for submission)
   - Don't initialize with README (we already have one)
   - Click "Create repository"

3. **Push to GitHub**
   ```bash
   git remote add origin https://github.com/YOUR_USERNAME/chitkara-bfhl-api.git
   git branch -M main
   git push -u origin main
   ```

### Phase 4: Deploy to Vercel (5 minutes)

1. **Login to Vercel**
   - Go to https://vercel.com
   - Click "Sign Up" or "Login"
   - Choose "Continue with GitHub"
   - Authorize Vercel

2. **Import Project**
   - Click "Add New..." → "Project"
   - Select your GitHub repository
   - Click "Import"

3. **Configure Project**
   - Framework Preset: Other
   - Build Command: `mvn clean package`
   - Output Directory: `target`
   - Install Command: (leave default)

4. **Environment Variables** (IMPORTANT!)
   - Click "Environment Variables"
   - Add variables:
     ```
     Name: OFFICIAL_EMAIL
     Value: your.email@chitkara.edu.in
     
     Name: GEMINI_API_KEY
     Value: your_gemini_api_key (or leave empty)
     ```

5. **Deploy**
   - Click "Deploy"
   - Wait 3-5 minutes for build
   - Once complete, you'll see "Congratulations!"

6. **Get Your URL**
   - Copy the deployment URL (e.g., `https://chitkara-bfhl-api.vercel.app`)

### Phase 5: Verify Deployment (5 minutes)

Test all endpoints with your Vercel URL:

```bash
# Replace YOUR_URL with your actual Vercel URL
export API_URL=https://YOUR_PROJECT.vercel.app

# 1. Health Check
curl $API_URL/health

# 2. Fibonacci
curl -X POST $API_URL/bfhl -H "Content-Type: application/json" -d '{"fibonacci": 7}'

# 3. Prime
curl -X POST $API_URL/bfhl -H "Content-Type: application/json" -d '{"prime": [2,4,7,9,11]}'

# 4. LCM
curl -X POST $API_URL/bfhl -H "Content-Type: application/json" -d '{"lcm": [12,18,24]}'

# 5. HCF
curl -X POST $API_URL/bfhl -H "Content-Type: application/json" -d '{"hcf": [24,36,60]}'

# 6. AI
curl -X POST $API_URL/bfhl -H "Content-Type: application/json" -d '{"AI": "What is the capital city of Maharashtra?"}'
```

## Alternative: Vercel CLI Deployment

If you prefer command line:

```bash
# Install Vercel CLI
npm install -g vercel

# Login
vercel login

# Deploy
cd c:\Users\DELL\Desktop\bajaj
vercel

# Follow prompts:
# - Link to existing project? No
# - What's your project's name? chitkara-bfhl-api
# - In which directory? ./
# - Want to override settings? No

# Add environment variables
vercel env add OFFICIAL_EMAIL
# Enter: your.email@chitkara.edu.in

vercel env add GEMINI_API_KEY
# Enter: your_api_key or press enter to skip

# Deploy to production
vercel --prod
```

## Troubleshooting

### Build Fails on Vercel

**Issue**: "Build failed: Maven not found"
**Solution**: Vercel should auto-detect Java. If not:
- Ensure `pom.xml` is in root directory
- Check vercel.json configuration

**Issue**: "Build failed: Java version mismatch"
**Solution**: Add to `vercel.json`:
```json
{
  "build": {
    "env": {
      "JAVA_VERSION": "17"
    }
  }
}
```

### Runtime Errors

**Issue**: 500 Internal Server Error
**Solution**: 
- Check Vercel logs: Project → Deployments → Click deployment → View Logs
- Ensure OFFICIAL_EMAIL environment variable is set
- Redeploy if you added variables after initial deployment

**Issue**: AI returns "Unknown" for all questions
**Solution**: 
- This is expected if GEMINI_API_KEY is not set
- Fallback responses work for common questions
- Not required for basic functionality

### Endpoint Not Found

**Issue**: 404 Not Found
**Solution**:
- Use full URL: `https://your-project.vercel.app/bfhl`
- Check vercel.json routes configuration
- Vercel may need 1-2 minutes after deployment to be fully active

## Vercel Dashboard Features

- **Logs**: View real-time logs
- **Analytics**: See API usage
- **Deployments**: View deployment history
- **Settings**: Update environment variables
- **Domains**: Add custom domain (optional)

## Production Checklist

- [ ] Application builds successfully locally
- [ ] All tests pass
- [ ] Email configured correctly
- [ ] GitHub repository is PUBLIC
- [ ] Vercel deployment successful
- [ ] All 6 endpoints tested and working
- [ ] API returns correct response structure
- [ ] No crashes on invalid input
- [ ] Error messages are clear
- [ ] GitHub URL noted for submission
- [ ] Vercel URL noted for submission

## Submission Format

When submitting, provide:

1. **GitHub Repository URL**: 
   ```
   https://github.com/YOUR_USERNAME/chitkara-bfhl-api
   ```

2. **Deployed API URL**:
   ```
   https://your-project.vercel.app
   ```

3. **Test Results**: 
   - All endpoints working
   - Response structure matches requirements
   - Error handling works

## Next Steps After Deployment

1. **Monitor**: Check Vercel dashboard for any errors
2. **Update**: If you need to make changes:
   ```bash
   # Make changes locally
   git add .
   git commit -m "Your changes"
   git push
   # Vercel auto-deploys on push
   ```

3. **Environment Variables**: Update in Vercel dashboard if needed
   - Go to Project → Settings → Environment Variables

## Support

If you encounter issues:
1. Check Vercel deployment logs
2. Review application.properties configuration
3. Verify environment variables are set
4. Test locally first before deploying

---

**Remember**: Your application must be publicly accessible for evaluation!
