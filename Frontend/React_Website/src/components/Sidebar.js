import React, {useState} from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as CgIcons from 'react-icons/cg'; 
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import SubMenu from './SubMenu';
import SidebarData from './SidebarData';
import { IconContext } from 'react-icons/lib';
import gymLogo from '../media/gympic.png';

const Nav = styled.div`
    background: #15171c;
    height: 80px;
    display: flex;
    justify-content: center;
    align-items: center;
    `;

const NavIcon = styled(Link)`
    margin-left: 2rem;
    font-size: 2rem;
    height: 80px;
    display: flex;
    justify-content: center;
    align-items: center;
    `;

const SidebarNav = styled.nav`
    background: #15171c;
    width: 300px;
    height: 100vh;
    display: flex;
    justify-content: center;
    position: fixed;
    top: 0;
    left: ${({ sidebar }) => (sidebar ? '0' : '-100%')};
    transition: 350ms;
    z-index: 10;
    `;

const SidebarWrap = styled.div`
    width: 100%
    `;

const GymLogoImg = styled(Link)`
    margin-left: 40rem;
    height: 75px;
    width: 80px;
    display: flex;
    `;

const ProfileIcon = styled(Link)`
    margin-left: 40rem;
    font-size: 2rem;
    height: 80px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    `;

const Sidebar = () => {

    const [sidebar, setSidebar] = useState(false);
    const showSidebar = () => setSidebar(!sidebar);

    return (
        <>
        <IconContext.Provider value={{ color: '#fff' }} >

            <Nav>
                <NavIcon to="#">
                    <FaIcons.FaBars onClick={showSidebar}/>
                </NavIcon>

                <GymLogoImg to="/Home">
                    <img src={gymLogo} alt="gymLogo" />
                </GymLogoImg>

                <ProfileIcon to="/Login">
                    <CgIcons.CgProfile/>
                </ProfileIcon>
                
            </Nav>


            <SidebarNav sidebar={sidebar}>
                <SidebarWrap>
                    <NavIcon to="#">
                        <AiIcons.AiOutlineClose onClick={showSidebar}/>
                    </NavIcon>
                    {SidebarData.map((item, index) =>{ 
                        return <SubMenu item={item} key={index} />;
                    })}
                </SidebarWrap>
            </SidebarNav>

        </IconContext.Provider>
        </>
    );
};

export default Sidebar;