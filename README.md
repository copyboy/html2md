# HTML2MD - Blog Article Converter

<div align="center">

[![Stars](https://img.shields.io/github/stars/copyboy/html2md?style=flat-square)](https://github.com/copyboy/html2md/stargazers)
[![Forks](https://img.shields.io/github/forks/copyboy/html2md?style=flat-square)](https://github.com/copyboy/html2md/network/members)
[![License](https://img.shields.io/github/license/copyboy/html2md?style=flat-square)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.x-brightgreen?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)

**Convert blog articles from Chinese platforms (CSDN, CNBLOG, WeChat) to Markdown/HTML format**

[中文](README.md) | [English](#english-version) | [Live Demo](https://api.zhangqingdong.cn/tool/html2md)

</div>

---

## ✨ Features

- ✅ **Multiple Platform Support**: CSDN, CNBLOG, WeChat Official Account
- ✅ **Dual Format Export**: Markdown and HTML
- ✅ **Batch Download**: Process multiple URLs at once (comma-separated)
- ✅ **CSDN Categories**: Download entire category/column articles
- ✅ **Clean Output**: Remove ads, comments, and unnecessary elements
- ✅ **Web UI**: Simple browser interface
- ✅ **API Mode**: RESTful API for integration

---

## 🚀 Quick Start

### Prerequisites

```bash
Java 17+
Maven 3.6+
```

### Installation & Run

```bash
# Clone repository
git clone https://github.com/copyboy/html2md.git
cd html2md

# Build
mvn clean package

# Run
java -jar target/html2md.jar

# Access in browser
http://localhost:8090/tool/html2md
```

### 🌐 Try Live Demo

**Online Tool**: https://api.zhangqingdong.cn/tool/html2md

Just paste your article URL and click convert!

---

## 📖 Usage Examples

### Single Article

```bash
# CSDN Article
https://blog.csdn.net/username/article/details/123456789

# CNBLOG Article  
https://www.cnblogs.com/username/p/123456.html

# WeChat Official Account
https://mp.weixin.qq.com/s/xxxxxxxxxxxxx
```

### Batch Download

```bash
# Multiple URLs (comma-separated)
https://blog.csdn.net/user1/article/details/111,
https://www.cnblogs.com/user2/p/222.html,
https://mp.weixin.qq.com/s/xxx
```

### CSDN Category

```bash
# Download entire category
https://blog.csdn.net/username/category_8584136.html
```

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot 2.x
- **HTML Parser**: Jsoup
- **Markdown Converter**: Flexmark
- **Web Server**: Embedded Tomcat

---

## 📊 Supported Platforms

| Platform | Status | Format | Batch | Notes |
|----------|--------|--------|-------|-------|
| CSDN | ✅ | MD/HTML | ✅ | Supports categories |
| CNBLOG | ✅ | MD/HTML | ✅ | Use HTML if MD has issues |
| WeChat | ✅ | MD/HTML | ✅ | Official Account only |
| Juejin | ⏳ | - | - | Coming soon |
| Jianshu | ⏳ | - | - | Coming soon |

---

## 🔧 API Usage

### Convert Article

```bash
POST http://localhost:8090/api/convert
Content-Type: application/json

{
  "url": "https://blog.csdn.net/xxx/article/details/123",
  "format": "markdown"  // or "html"
}
```

### Response

```json
{
  "success": true,
  "title": "Article Title",
  "content": "# Article Content\n\n...",
  "author": "Author Name"
}
```

---

## 🎨 Screenshots

<details>
<summary>Click to expand</summary>

**Web Interface**:
- Simple input form
- Real-time conversion
- Download button

**Output Example**:
- Clean Markdown format
- Preserved code blocks
- Maintained image links

</details>

---

## 🤝 Contributing

Contributions are welcome!

### Add New Platform Support

1. Add new platform enum in `HostEnums`
2. Get page content ID selector
3. Implement parser logic
4. Add tests
5. Submit PR

### Code Style

```bash
# Run tests
mvn test

# Code format
mvn spotless:apply
```

---

## 📝 Changelog

### v1.1.0 (Latest)
- ✨ Added WeChat Official Account support
- 🐛 Fixed CNBLOG code block issues
- 📝 Improved documentation

### v1.0.0
- 🎉 Initial release
- ✅ CSDN & CNBLOG support

---

## 🙏 Acknowledgements

- [Jsoup](https://jsoup.org/) - HTML parser
- [Flexmark](https://github.com/vsch/flexmark-java) - Markdown processor
- [Spring Boot](https://spring.io/projects/spring-boot) - Application framework

---

## 📄 License

[MIT License](LICENSE)

---

## 💬 Contact & Support

- **Issues**: [GitHub Issues](https://github.com/copyboy/html2md/issues)
- **Email**: copy_boy@126.com
- **Blog**: [i.zhangqingdong.cn](https://i.zhangqingdong.cn)

---

## ⭐ Star History

If you find this project helpful, please give it a star! ⭐

---

<div align="center">

Made with ❤️ by [copyboy](https://github.com/copyboy)

</div>

---

# English Version

<div align="center">

**🌏 HTML2MD - Blog Article Converter**

Convert Chinese blog articles to Markdown/HTML format with ease

</div>

## What is HTML2MD?

HTML2MD is a Java-based tool that helps you convert blog articles from popular Chinese platforms (CSDN, CNBLOG, WeChat Official Accounts) into clean Markdown or HTML format.

Perfect for:
- 📚 **Content Migration**: Move your articles to new platforms
- 💾 **Backup**: Archive your blog posts locally
- 📝 **Reuse**: Repurpose content for documentation
- 🔄 **Format Conversion**: Convert between HTML and Markdown

## Key Features

1. **Multi-Platform Support**: Works with CSDN, CNBLOG, and WeChat
2. **Clean Output**: Removes ads, comments, and clutter
3. **Batch Processing**: Convert multiple articles at once
4. **Live Demo**: Try it online without installation
5. **API Available**: Integrate into your workflow

## Quick Start Guide

```bash
# 1. Clone
git clone https://github.com/copyboy/html2md.git

# 2. Build
mvn clean package

# 3. Run
java -jar target/html2md.jar

# 4. Open browser
http://localhost:8090/tool/html2md
```

## Usage

1. Paste article URL
2. Select output format (Markdown/HTML)
3. Click "Convert"
4. Download result

**That's it!** 🎉

## Tech Stack

- Java 17+
- Spring Boot 2.x
- Jsoup (HTML parsing)
- Flexmark (Markdown)

## Contributing

PRs welcome! Please:
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push and submit a PR

## License

MIT License - see [LICENSE](LICENSE) file

---

<div align="center">

**[⬆ Back to Top](#html2md---blog-article-converter)**

</div>

